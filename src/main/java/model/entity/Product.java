package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "findProductByName", query = "SELECT p FROM Product p WHERE p.name = :name")
})
@Data
@Builder
@AllArgsConstructor
public class Product extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence",allocationSize = 75)
    private long id;
    @Column(name = "product_name", nullable = false, length = 50)
    private String name;
    @Column(name = "description", nullable = false, length = 80)
    private String description;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @ElementCollection
    @Column(name = "comment", nullable = false, length = 150, updatable = false)
    private List<String> comment;
    @Column(name = "like_count", nullable = false)
    private int like;
    @Column(name = "dislike_count", nullable = false)
    private int dislike;
    @Column(name = "star", nullable = false, updatable = false)
    private double star;
    @Column(name = "remain_count", nullable = false)
    private int remainCount;
    @Builder.Default
    @Column(name = "product_status", nullable = false)
    private boolean status = true;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    @Embedded
    private ProductDetail productDetails;

    public Product(){
        setDescription(description);
        setName(name);
    }

    public void setDescription(String description) {
        this.description = description.toLowerCase();
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setComment(String comment){
        this.comment.add(comment);
    }

    public void increaseLike() {
        like++;
    }

    public void increaseDislike() {
        dislike++;
    }

    public void setStar(double star) throws ApplicationException {
        if (star >= 1 && star <= 5) {
            this.star = (this.star + star) / 2;
        } else {
            throw new ApplicationException(ExceptionEnum.WRONG_STAR_POINT);
        }
    }

    public void decreaseRemainCount(){
        --this.remainCount;
    }
}
