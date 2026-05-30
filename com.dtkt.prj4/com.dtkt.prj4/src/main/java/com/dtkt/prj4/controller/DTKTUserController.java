package com.dtkt.prj4.controller;

import com.dtkt.prj4.entity.Product;
import com.dtkt.prj4.repository.DTKTProductCommentRepository;
import com.dtkt.prj4.service.DTKTCategoryService;
import com.dtkt.prj4.service.DTKTProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DTKTUserController {

    private final DTKTProductService productService;

    private final DTKTCategoryService categoryService;

    private final DTKTProductCommentRepository commentRepository;

    public DTKTUserController(
            DTKTProductService productService,
            DTKTCategoryService categoryService,
            DTKTProductCommentRepository commentRepository
    ) {

        this.productService = productService;
        this.categoryService = categoryService;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/")
    public String home(
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

        return "user/index";
    }

    @GetMapping("/product/{id}")
    public String productDetail(
            @PathVariable Long id,
            Model model
    ) {
        Product product =
                productService.getProductById(id);
        model.addAttribute(
                "product",
                product
        );
        model.addAttribute(
                "comments",
                commentRepository
                        .findByProductIdAndIsVisibleTrue(id)
        );
        return "user/product-detail";
    }
}
