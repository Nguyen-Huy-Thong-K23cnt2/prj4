package com.dtkt.prj4.service;

import com.dtkt.prj4.entity.Category;
import com.dtkt.prj4.repository.DTKTCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DTKTCategoryService {

    private final DTKTCategoryRepository categoryRepository;

    public DTKTCategoryService(
            DTKTCategoryRepository categoryRepository
    ) {

        this.categoryRepository = categoryRepository;
    }

    // =========================
    // GET ALL
    // =========================
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public Category getCategoryById(
            Long id
    ) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        ));
    }

    // =========================
    // CREATE
    // =========================
    public Category createCategory(
            Category category
    ) {

        return categoryRepository.save(category);
    }

    // =========================
    // UPDATE
    // =========================
    public Category updateCategory(
            Long id,
            Category request
    ) {

        Category category =
                categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"
                                ));

        category.setName(
                request.getName()
        );

        return categoryRepository.save(category);
    }

    // =========================
    // DELETE
    // =========================
    public String deleteCategory(
            Long id
    ) {

        categoryRepository.deleteById(id);

        return "Delete success";
    }
}