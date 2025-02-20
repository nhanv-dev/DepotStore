package com.spring.server.service.implement;

import com.spring.server.model.dto.ProductDto;
import com.spring.server.model.entity.Product;
import com.spring.server.model.entity.RatingInfo;
import com.spring.server.model.mapper.ProductDetailMapper;
import com.spring.server.model.mapper.ProductMapper;
import com.spring.server.repository.ProductRepo;
import com.spring.server.repository.RatingInfoRepo;
import com.spring.server.repository.ShopRepo;
import com.spring.server.service.ProductService;
import com.spring.server.util.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private RatingInfoRepo ratingInfoRepo;

    @Override
    public ProductDto findOneById(Long id) {
        Product product = productRepo.findOneByIdAndIsPublicAndIsDeleted(id, true, false);
        if (product == null) return null;
        return ProductDetailMapper.toDto(product);
    }

    @Override
    public ProductDto findOneById(Long id, Boolean isPublic, Boolean isDeleted) {
        Product product = null;
        if (isPublic == null) product = productRepo.findOneByIdAndIsDeleted(id, isDeleted);
        else product = productRepo.findOneByIdAndIsPublicAndIsDeleted(id, isPublic, isDeleted);
        if (product == null) return null;
        return ProductDetailMapper.toDto(product);
    }

    @Override
    public ProductDto findOneBySlug(String slug) {
        Product product = productRepo.findOneBySlugAndIsPublicAndIsDeleted(slug, true, false);
        if (product == null) return null;
        return ProductDetailMapper.toDto(product);
    }

    @Override
    public Page<ProductDto> findByOrderByCreatedAt(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ProductMapper.toDto(productRepo.findAllByIsPublicAndIsDeleted(pageable, true, false));
    }

    @Override
    public Page<ProductDto> findByCategorySlug(int page, int size, String categorySlug) {
        Pageable pageable = PageRequest.of(page, size);
        return ProductMapper.toDto(productRepo.findAllByCategoryAndIsDeleted(pageable, true, categorySlug, categorySlug, false));
    }

    @Override
    public Page<ProductDto> findByShopId(int page, int size, Long shopId, Boolean isPublic, Boolean isDeleted) {
        Pageable pageable = PageRequest.of(page, size);
        if (isPublic == null) {
            Page<Product> products = productRepo.findAllByShop_IdAndIsDeleted(pageable, shopId, isDeleted);
            return ProductMapper.toDto(products);
        }
        Page<Product> products = productRepo.findAllByShop_IdAndIsPublicAndIsDeleted(pageable, shopId, isPublic, isDeleted);
        return ProductMapper.toDto(products);
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product product = ProductDetailMapper.toEntity(productDto);
        RatingInfo ratingInfo = ratingInfoRepo.save(new RatingInfo());
        product.setRatingInfo(ratingInfo);
        product = productRepo.save(product);
        product.setSlug(SlugGenerator.toSlug(product.getName() + "-" + product.getId()));
        product = productRepo.save(product);
        shopRepo.updateProductTotalById(product.getShop().getId());
        return ProductDetailMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto) {
        Product savedProduct = productRepo.findOneById(productDto.getId());
        if (savedProduct == null) return null;
        Product product = ProductDetailMapper.toEntity(productDto);
        product.setSlug(SlugGenerator.toSlug(product.getName() + "-" + product.getId()));
        product = productRepo.save(product);
        shopRepo.updateProductTotalById(savedProduct.getShop().getId());
        return ProductDetailMapper.toDto(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product savedProduct = productRepo.findOneById(id);
        productRepo.deleteById(id);
        shopRepo.updateProductTotalById(savedProduct.getShop().getId());
    }


    @Override
    public Page<ProductDto> searchProducts(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepo.searchProductByName(pageable, name);
        if (products == null) return null;
        return ProductMapper.toDto(products);
    }

}
