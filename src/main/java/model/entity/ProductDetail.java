package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Embeddable
public class ProductDetail implements Serializable {

    @Column(name = "color", nullable = false, length = 15)
    private String color;

    @Column(name = "size", nullable = false, length = 8)
    private String size;

    @Column(name = "count", nullable = false)
    private int count;

    public ProductDetail() {
        setSize(size);
    }

    public void setSize(String size){
        this.size = size.toUpperCase();
    }
}

