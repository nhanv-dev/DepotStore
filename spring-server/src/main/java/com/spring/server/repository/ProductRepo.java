package com.spring.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.server.model.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query()
    Product findOneById(Long id);

    @Query()
    Product findOneByIdAndIsDeleted(Long id, Boolean isDeleted);

    @Query()
    Product findOneByIdAndIsPublicAndIsDeleted(Long id, Boolean isPublic, Boolean isDeleted);

    @Query()
    Product findOneBySlugAndIsPublicAndIsDeleted(String slug, Boolean isPublic, Boolean isDeleted);

    @Query()
    Page<Product> findAllByIsPublicAndIsDeleted(Pageable pageable, Boolean isPublic, Boolean isDeleted);

    @Query("select p from Product p where (p.category.slug = :categorySlug or p.subCategory.slug = :subCategorySlug) and p.isPublic = :isPublic and p.isDeleted = :isDeleted")
    Page<Product> findAllByCategoryAndIsDeleted(Pageable pageable, boolean isPublic, String categorySlug,
            String subCategorySlug, Boolean isDeleted);

    @Query()
    Page<Product> findAllByShop_IdAndIsDeleted(Pageable pageable, Long shopId, Boolean isDeleted);

    @Query()
    Page<Product> findAllByShop_IdAndIsPublicAndIsDeleted(Pageable pageable, Long shopId, Boolean isPublic,
            Boolean isDeleted);

    @Modifying
    @Query(value = "UPDATE Product p set p.orderCount = p.orderCount  + :count WHERE p.id = :id")
    void increaseOrderCount(Long id, Integer count);

    @Modifying
    @Query(value = "UPDATE Product p set p.isDeleted = true WHERE p.id = :id")
    void deleteById(Long id);

    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%', :name, '%')" +
            "Or p.keywords LIKE CONCAT('%', :name, '%')")
    Page<Product> searchProductByName(Pageable pageable, String name);
}