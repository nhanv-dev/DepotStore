package com.spring.server.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attribute")
@SQLDelete(sql = "UPDATE attribute SET is_deleted = true WHERE id=?")
public class ProductAttribute extends BaseEntity implements Serializable {
    @Column
    private String name;
    @Column()
    private boolean isDeleted;
    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "is_deleted=false")
    private Set<ProductAttributeOption> options = new HashSet<>();
}
