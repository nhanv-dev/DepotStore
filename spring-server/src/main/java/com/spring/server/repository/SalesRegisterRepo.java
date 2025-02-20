package com.spring.server.repository;

import com.spring.server.model.entity.SalesRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalesRegisterRepo extends JpaRepository<SalesRegister, Long> {
    @Query("select s from SalesRegister s")
    Page<SalesRegister> findAll(Pageable pageable);
    @Query()
    SalesRegister findOneById(Long id);
    @Query()
    SalesRegister findOneByUser_Id(Long userId);


}