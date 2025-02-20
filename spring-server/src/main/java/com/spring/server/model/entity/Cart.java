package com.spring.server.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<CartItem> items = new HashSet<>();

    public CartItem updateItem(CartItem item) {
        if (items == null) items = new HashSet<>();
        for (CartItem i : items) {
            if (item.getProduct().getId().equals(i.getProduct().getId())) {
                if (item.getVariant() != null) {
                    if (i.getVariant() != null && item.getVariant().getId().equals(i.getVariant().getId())) {
                        i.setQuantity(i.getQuantity() + item.getQuantity());
                        return i;
                    }
                } else {
                    i.setQuantity(i.getQuantity() + item.getQuantity());
                    return i;
                }
            }
        }
        return null;
    }

    public boolean containItem(CartItem item) {
        for (CartItem i : items) {
            if (item.getProduct().getId().equals(i.getProduct().getId())) {
                if (item.getVariant() != null) {
                    if (i.getVariant() != null && item.getVariant().getId().equals(i.getVariant().getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
