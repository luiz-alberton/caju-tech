package tech.caju.authorizer.balance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, Long>, BalanceRepositoryCustom {

}
