package com.spring.server.model.entity;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "deal")
@SQLDelete(sql = "UPDATE deal SET is_deleted = true WHERE id=?")
public class Deal extends BaseEntity implements Serializable {
    @Column(columnDefinition = "decimal(15,2)", nullable = false)
    private double price;
    @Column(columnDefinition = "decimal(15,2)")
    private double finalPrice;
    @Column()
    private double discountPercent;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;
}
