package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name = "findAllCategories", query = "SELECT c FROM Category c"),
        @NamedQuery(name = "findCategoryByName", query = "SELECT c FROM Category c WHERE c.name = :name")
})
@Data
@Builder
@AllArgsConstructor
public class Category extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "category_sequence")
    @SequenceGenerator(name = "category_sequence", allocationSize = 75)
    private long id;

    @Column(name = "categoryName", nullable = false )
    private String name;
    @Column(name = "description", nullable = false, length = 20)
    private String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Brand brand;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;
    @Builder.Default
    @Column(name = "category_status", nullable = false)
    private boolean status = true;

    public Category(){
        setDescription(description);
        setName(name);
    }
    public void setDescription(String description) {
        this.description = description.toLowerCase();
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

}



