package tech.caju.authorizer.balance;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import tech.caju.authorizer.account.Account;
import tech.caju.authorizer.category.Category;

@Entity
@Table(name = "balance")
@SequenceGenerator(name = "balance", sequenceName = "bal_seq", allocationSize = 1)
@Getter
@Setter
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "balance")
    private Long id;

    @Version
    private Long version;

    @Convert(converter = AmountConverter.class)
    private Amount amount;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

}
