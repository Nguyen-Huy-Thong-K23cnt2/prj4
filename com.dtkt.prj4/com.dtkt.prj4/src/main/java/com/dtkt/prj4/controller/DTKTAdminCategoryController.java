package com.dtkt.prj4.controller;

import com.dtkt.prj4.entity.Category;
import com.dtkt.prj4.service.DTKTCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class DTKTAdminCategoryController {
    private final DTKTCategoryService categoryService;
    public DTKTAdminCategoryController(
            DTKTCategoryService categoryService
    ) {

        this.categoryService = categoryService;
    }
    // CATEGORY PAGE
    @GetMapping
    public String categoryPage(
            Model model
    ) {
        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );
        model.addAttribute(
                "category",
                new Category()
        );
        model.addAttribute(
                "editMode",
                false
        );
        return "admin/categories";
    }
    // CREATE CATEGORY
    @PostMapping("/create")
    public String createCategory(
            @ModelAttribute Category category
    ) {
        categoryService.createCategory(
                category
        );
        return "redirect:/admin/categories";
    }
    // EDIT CATEGORY
    @GetMapping("/edit/{id}")
    public String editCategory(
            @PathVariable Long id,
            Model model
    ) {
        Category category =
                categoryService.getCategoryById(id);
        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );
        model.addAttribute(
                "category",
                category
        );
        model.addAttribute(
                "editCategoryId",
                id
        );
        model.addAttribute(
                "editMode",
                true
        );
        return "admin/categories";
    }
    // UPDATE CATEGORY
    @PostMapping("/update/{id}")
    public String updateCategory(
            @PathVariable Long id,
            @ModelAttribute Category category
    ) {
        categoryService.updateCategory(
                id,
                category
        );
        return "redirect:/admin/categories";
    }
    // DELETE CATEGORY
    @GetMapping("/delete/{id}")
    public String deleteCategory(
            @PathVariable Long id
    ) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}