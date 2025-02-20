package com.spring.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.server.model.entity.SubCategory;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
    @Query()
    List<SubCategory> findByCategoryId(long categoryId);

    @Query()
    SubCategory findOneBySlug(String slug);

}