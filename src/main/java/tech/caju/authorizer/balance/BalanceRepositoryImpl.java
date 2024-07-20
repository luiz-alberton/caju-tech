package tech.caju.authorizer.balance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceRepositoryImpl implements BalanceRepositoryCustom {

  @PersistenceContext
  EntityManager em;

  @Override
  public Balance getBalanceFromAccount(Long accountId, String mcc) {

    var sql = """
        SELECT
          b
        FROM
          Balance b
        JOIN
          b.account a
        JOIN
          b.category cat
        JOIN
          cat.classifications cla
        WHERE
          a.id = :accountId
          AND cla.code = :mccCode
        """;

    TypedQuery<Balance> query = em.createQuery(sql, Balance.class);
    query.setParameter("accountId", accountId);
    query.setParameter("mccCode", mcc);
    try {
      var balance = query.getSingleResult();
      return balance.getAmount().toLong() == 0L ? null : balance;
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public Balance getBalanceCashFromAccount(Long accountId) {
    var sql = """
        SELECT
          b
        FROM
          Balance b
        JOIN
          b.account a
        JOIN
          b.category cat
        WHERE
          a.id = :accountId
          AND cat.name = :cashName
        """;

    TypedQuery<Balance> query = em.createQuery(sql, Balance.class);
    query.setParameter("accountId", accountId);
    query.setParameter("cashName", "CASH");
    try {
      var balance = query.getSingleResult();
      return balance.getAmount().toLong() == 0L ? null : balance;
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public Balance getBalanceCategoryFromMerchant(Long accountId, String merchant) {
    var sql = """
        SELECT
          b
        FROM
          Balance b
        JOIN
          b.account a
        JOIN
          b.category cat
        JOIN
          Merchant m ON
          m.category = b.category
        WHERE
          a.id = :accountId
          AND m.name = :merchant
        """;

    TypedQuery<Balance> query = em.createQuery(sql, Balance.class);
    query.setParameter("accountId", accountId);
    query.setParameter("merchant", merchant);
    try {
      var balance = query.getSingleResult();
      return balance.getAmount().toLong() == 0L ? null : balance;
    } catch (NoResultException e) {
      return null;
    }
  }

}
