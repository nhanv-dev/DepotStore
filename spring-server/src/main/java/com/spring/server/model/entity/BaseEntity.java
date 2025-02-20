package com.spring.server.model.entity;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
//    @Column(columnDefinition = "timestamp default NOW()", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @LastModifiedDate
    protected Date createdAt;
//    @Column(columnDefinition = "timestamp default NOW() ON UPDATE NOW()")
    @UpdateTimestamp
    @LastModifiedDate
    protected Date updatedAt;
}
