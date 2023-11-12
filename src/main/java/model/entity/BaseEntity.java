package model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_name")
    @SequenceGenerator(name = "sequence_name",allocationSize = 75)
    private long id;
    private boolean status = true;
}
