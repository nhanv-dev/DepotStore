package com.spring.server.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.spring.server.model.dto.CategoryDto;
import com.spring.server.model.dto.SubCategoryDto;
import com.spring.server.model.entity.Category;
import com.spring.server.model.entity.SubCategory;
import com.spring.server.model.mapper.CategoryMapper;
import com.spring.server.model.mapper.SubCategoryMapper;
import com.spring.server.repository.CategoryRepo;
import com.spring.server.repository.SubCategoryRepo;
import com.spring.server.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @Override
    public CategoryDto findOneById(Long id) {
        Optional<Category> optional = categoryRepo.findById(id);
        if (optional.isEmpty()) return null;
        return CategoryMapper.toDto(optional.orElse(null));
    }

    @Override
    public CategoryDto findOneBySlug(String slug) {
        Category category = categoryRepo.findOneBySlug(slug);
        if (category != null) return CategoryMapper.toDto(category);
        SubCategory subCategory = subCategoryRepo.findOneBySlug(slug);
        if (subCategory == null) return null;
        category = categoryRepo.findOneById(subCategory.getCategory().getId());
        return CategoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> list = categoryRepo.findAll();
        return CategoryMapper.toDto(list);
    }

    @Override
    public List<CategoryDto> findAllWithoutSub() {
        List<Category> list = categoryRepo.findAll();
        return CategoryMapper.toDtoWithoutSub(list);
    }

    @Override
    public List<CategoryDto> findByCategoryId(Long id) {
        return null;
    }

    @Override
    public Page<CategoryDto> findByPageable(Pageable pageable) {
        Page<Category> entities = categoryRepo.findAll(pageable);
        return entities.map(new Function<Category, CategoryDto>() {
            @Override
            public CategoryDto apply(Category category) {
                return CategoryMapper.toDto(category);
            }
        });
    }

    @Override
    public Page<CategoryDto> findByPageableWithoutSub(Pageable pageable) {
        Page<Category> entities = categoryRepo.findAll(pageable);
        return entities.map(new Function<Category, CategoryDto>() {
            @Override
            public CategoryDto apply(Category category) {
                return CategoryMapper.toDtoWithoutSub(category);
            }
        });
    }

    @Override
    public List<SubCategoryDto> findSubCategoryByCategoryId(Long id) {
        List<SubCategory> list = subCategoryRepo.findByCategoryId(id);
        return SubCategoryMapper.toDto(list);
    }

    @Override
    public void update(CategoryDto category) {
        Category categoryEntity = CategoryMapper.toEntity(category);
        categoryRepo.save(categoryEntity);
    }

}
