package tech.caju.authorizer.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.caju.authorizer.balance.Balance;

@Entity
@Table(name = "account")
@SequenceGenerator(name = "account", sequenceName = "acc_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {
    @Id
    @GeneratedValue(generator = "account", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "account")
    private List<Balance> balances;


    public Account(Long accountId) {
        this.id = accountId;
    }
}
