package tech.caju.authorizer.account;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  @Transactional
  public Account findAccountByIdWithBalance(Long id) {
    var acc = accountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    acc.getBalances().size();
    return acc;
  }

  public Account findAccountById(Long id) {
    return accountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public long count() {
    return accountRepository.count();
  }

}
