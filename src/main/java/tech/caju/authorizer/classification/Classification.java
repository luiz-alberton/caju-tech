package tech.caju.authorizer.classification;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import tech.caju.authorizer.category.Category;

@Entity
@Table(name = "classification")
@SequenceGenerator(name = "classification", sequenceName = "cla_seq", allocationSize = 1)
@Getter
@Setter
public class Classification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classification")
    private Long id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
