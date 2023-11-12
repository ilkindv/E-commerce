package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "findAllOrders", query = "SELECT o FROM Order o"),
})
@Data
@Builder
@AllArgsConstructor
public class Order extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence",allocationSize = 75)
    private long id;
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    @Column(name = "adress", nullable = false)
    private String address;
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    public Order(){

    }
}
