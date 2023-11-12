package model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "carts")
@NamedQueries({
        @NamedQuery(name = "findAllCarts", query = "SELECT b FROM Cart b"),
        @NamedQuery(name = "findCartByTotalAmount", query = "SELECT b FROM Cart b WHERE b.totalAmount = :totalAmount"),
})
@Data
public class Cart extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_name")
    @SequenceGenerator(name = "sequence_name",allocationSize = 75)
    private long id;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Product> buyProduct;
    @Column(name = "count", nullable = false)
    private int count;
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;
    @Builder.Default
    @Column(name = "cart_status", nullable = false)
    private boolean status = true;

    public Cart() {

    }

    public void increaseCount(int count) {
        this.count += count;
    }

    public void decreaseCount(int count) {
        this.count -= count;
    }

    public void increaseTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = this.totalAmount.add(totalAmount);
    }

    public void decreaseTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = this.totalAmount.subtract(totalAmount);
    }

    public void addProduct(Product product) {
        this.buyProduct.add(product);
    }

    public void deleteProduct(Product product) {
        this.buyProduct.remove(product);
    }

    public BigDecimal sumProductsPriceOnCart(List<Product> productsOnCart){
        BigDecimal sumPrice = BigDecimal.ZERO;

        sumPrice = productsOnCart.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sumPrice;
    }
}
