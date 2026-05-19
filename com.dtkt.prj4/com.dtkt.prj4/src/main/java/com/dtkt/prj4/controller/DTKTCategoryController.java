package com.dtkt.prj4.controller;

import com.dtkt.prj4.entity.Category;
import com.dtkt.prj4.service.DTKTCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dtkt/categories")
public class DTKTCategoryController {

    private final DTKTCategoryService categoryService;

    public DTKTCategoryController(
            DTKTCategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    // GET ALL
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Category getCategoryById(
            @PathVariable Long id
    ) {
        return categoryService.getCategoryById(id);
    }

    // CREATE
    @PostMapping
    public Category createCategory(
            @RequestBody Category category
    ) {
        return categoryService.createCategory(category);
    }
}