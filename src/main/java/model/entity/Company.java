package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Entity(name = "trendyol")
@Data
@Builder
@AllArgsConstructor
public class Company  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Builder.Default
    private  long id = 1;
    @Builder.Default
    @Column(name = "company_name", nullable = false, updatable = false)
    private String name = "Trendyol";
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;
    @OneToMany(mappedBy = "trendyol", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> buyProductList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trendyol")
    private Set<Brand> brand;

    @Getter
    private static final Company instance = new Company();

    public Company() {

    }
    public void incrementTotalAmount(BigDecimal totalAmount){
        this.totalAmount = this.totalAmount.add(totalAmount);
    }

}
