package com.spring.server.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private double discountPercent;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double finalPrice;
    @Column(columnDefinition = "bigint not null", nullable = false)
    private int quantity;
    @Column(columnDefinition = "boolean default false")
    private Boolean isEvaluated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", referencedColumnName = "id")
    private ProductVariant variant;

    @PrePersist
    public void prePersist() {
        if (isEvaluated == null)
            isEvaluated = false;
    }

}
