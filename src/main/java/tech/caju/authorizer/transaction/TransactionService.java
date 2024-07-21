package tech.caju.authorizer.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.caju.authorizer.balance.BalanceService;
import tech.caju.authorizer.exceptions.InsuficientAmountException;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final BalanceService balanceService;
  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;

  @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class)
  public void handleTransaction(TransactionDto dto) {
    var accountId = dto.account();
    var balance = balanceService.getBalanceFromAccountByMcc(accountId, dto.mcc())
        .or(() -> balanceService.getBalanceCategoryFromMerchant(accountId, dto.merchant()))
        .or(() -> balanceService.getBalanceCashFromAccount(accountId))
        .orElseThrow(RuntimeException::new);

    balance.getAmount().subtract(dto.totalAmount());

    if (balance.getAmount().toLong() < 0) throw new InsuficientAmountException();

    balanceService.save(balance);
    transactionRepository.save(transactionMapper.toTransaction(dto));
  }
}
