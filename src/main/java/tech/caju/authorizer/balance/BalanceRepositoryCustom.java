package tech.caju.authorizer.balance;

public interface BalanceRepositoryCustom {
  Balance getBalanceFromAccount(Long accountId, String mcc);

  Balance getBalanceCashFromAccount(Long accountId);

  Balance getBalanceCategoryFromMerchant(Long accountId, String merchant);
}
