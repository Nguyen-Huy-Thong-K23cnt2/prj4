package com.dtkt.prj4.controller;

import com.dtkt.prj4.dto.DTKTProductRequestDTO;
import com.dtkt.prj4.entity.Product;
import com.dtkt.prj4.service.DTKTCategoryService;
import com.dtkt.prj4.service.DTKTProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class DTKTAdminProductController {
    private final DTKTProductService productService;
    private final DTKTCategoryService categoryService;
    public DTKTAdminProductController(
            DTKTProductService productService,
            DTKTCategoryService categoryService
    ) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // PRODUCT PAGE
    @GetMapping
    public String productPage(
            Model model
    ) {
        model.addAttribute(
                "products",
                productService.getAllProducts()
        );
        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );
        model.addAttribute(
                "productRequest",
                new DTKTProductRequestDTO()
        );
        model.addAttribute(
                "editMode",
                false
        );
        return "admin/products";
    }

    // CREATE PRODUCT
    @PostMapping("/create")
    public String createProduct(
            @ModelAttribute
            DTKTProductRequestDTO request
    ) {
        productService.createProduct(request);
        return "redirect:/admin/products";
    }

    // EDIT PRODUCT
    @GetMapping("/edit/{id}")
    public String editProduct(
            @PathVariable Long id,
            Model model
    ) {
        Product product =
                productService.getProductById(id);
        DTKTProductRequestDTO dto =
                new DTKTProductRequestDTO();
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDiscount(product.getDiscount());
        dto.setQuantity(product.getQuantity());
        dto.setSize(product.getSize());
        dto.setColor(product.getColor());
        dto.setDescription(
                product.getDescription()
        );
        dto.setCategoryId(
                product.getCategory().getId()
        );
        model.addAttribute(
                "products",
                productService.getAllProducts()
        );
        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );
        model.addAttribute(
                "productRequest",
                dto
        );
        model.addAttribute(
                "editProductId",
                id
        );
        model.addAttribute(
                "editMode",
                true
        );
        return "admin/products";
    }

    // UPDATE PRODUCT
    @PostMapping("/update/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute
            DTKTProductRequestDTO request
    ) {
        productService.updateProduct(
                id,
                request
        );
        return "redirect:/admin/products";
    }

    // DELETE PRODUCT
    @GetMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}