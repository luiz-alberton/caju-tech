package tech.caju.authorizer.balance;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

  private final BalanceRepository balanceRepository;

  public Optional<Balance> getBalanceFromAccountByMcc(Long accountId, String mcc) {
    return Optional.ofNullable(balanceRepository.getBalanceFromAccountByMcc(accountId, mcc));
  }

  public Optional<Balance> getBalanceCashFromAccount(Long accountId) {
    return Optional.ofNullable(balanceRepository.getBalanceCashFromAccount(accountId));
  }

  public Optional<Balance> getBalanceCategoryFromMerchant(Long accountId, String merchant) {
    return Optional.ofNullable(balanceRepository.getBalanceCategoryFromMerchant(accountId, merchant));
  }

  public void save(Balance balance) {
    balanceRepository.save(balance);
  }
}
