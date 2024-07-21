package tech.caju.authorizer.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import tech.caju.authorizer.balance.Amount;
import tech.caju.authorizer.balance.AmountConverter;

@Entity
@Table(name = "transaction")
@SequenceGenerator(name = "transaction", sequenceName = "tra_seq", allocationSize = 1)
@Getter
@Setter
public class Transaction implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction")
  private Long id;

  @Column(name = "account_id")
  private Long account;

  @Column(name = "total_amount")
  @Convert(converter = AmountConverter.class)
  private Amount totalAmount;

  private String mcc;

  @Column(name = "merchant_name")
  private String merchant;

}
