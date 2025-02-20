package com.spring.server.model.entity;

import com.spring.server.model.constant.ERole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "role")
@NoArgsConstructor
public class Role extends BaseEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column
    private ERole type;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(ERole type) {
        this.type = type;
    }
}
