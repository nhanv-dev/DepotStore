package com.spring.server.model.entity;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attribute_option")
@SQLDelete(sql = "UPDATE attribute_option SET is_deleted = true WHERE id=?")
public class ProductAttributeOption extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private String name, value;
    @Column()
    private String image;
    @Column()
    private boolean isDeleted = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    private ProductAttribute attribute;
}
