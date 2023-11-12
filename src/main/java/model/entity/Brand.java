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
@Table(name = "brands")
@NamedQueries({
        @NamedQuery(name = "findAllBrands", query = "SELECT b FROM Brand b"),
        @NamedQuery(name = "findBrandByName", query = "SELECT b FROM Brand b WHERE b.name = :name")
})
@Data
@Builder
@AllArgsConstructor
public class Brand extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "brand_sequence")
    @SequenceGenerator(name = "brand_sequence",allocationSize = 75)
    private long id;

    @Column(name = "brand_name", nullable = false )
    private String name;
    @Column(name = "description", nullable = false, length = 20)
    private String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Company company;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
    private Set<Category> categories;
    @Builder.Default
    @Column(name = "brand_status", nullable = false)
    private boolean status = true;


    public Brand(){
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
