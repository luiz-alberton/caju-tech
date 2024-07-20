package tech.caju.authorizer.category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import tech.caju.authorizer.balance.Balance;
import tech.caju.authorizer.classification.Classification;

@Entity
@Table(name = "category")
@SequenceGenerator(name = "category", sequenceName = "cat_seq", allocationSize = 1)
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Classification> classifications;

    @OneToOne(mappedBy = "category")
    private Balance balance;

}
