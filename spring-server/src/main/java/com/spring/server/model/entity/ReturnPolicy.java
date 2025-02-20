package com.spring.server.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "return_policy")
public class ReturnPolicy extends BaseEntity implements Serializable {
    @Getter
    @Setter
    @Column
    private String title, tooltipTitle, tooltipContent;

}
