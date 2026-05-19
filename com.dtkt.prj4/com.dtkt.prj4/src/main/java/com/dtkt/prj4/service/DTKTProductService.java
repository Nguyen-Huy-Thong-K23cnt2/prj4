package com.dtkt.prj4.service;

import com.dtkt.prj4.dto.DTKTProductRequestDTO;
import com.dtkt.prj4.entity.Category;
import com.dtkt.prj4.entity.Product;
import com.dtkt.prj4.repository.DTKTCategoryRepository;
import com.dtkt.prj4.repository.DTKTProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DTKTProductService {

    private final DTKTProductRepository productRepository;
    private final DTKTCategoryRepository categoryRepository;

    public DTKTProductService(
            DTKTProductRepository productRepository,
            DTKTCategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // GET ALL
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET BY ID
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
    }

    // CREATE
    public Product createProduct(DTKTProductRequestDTO request) {

        Category category = categoryRepository.findById(
                request.getCategoryId()
        ).orElseThrow(() ->
                new RuntimeException("Category not found"));

        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDiscount(request.getDiscount());
        product.setQuantity(request.getQuantity());
        product.setSize(request.getSize());
        product.setColor(request.getColor());
        product.setDescription(request.getDescription());
        product.setSoldCount(0);
        product.setIsActive(true);
        product.setIsFeatured(false);
        product.setCategory(category);
        return productRepository.save(product);
    }

    // DELETE
    public String deleteProduct(Long id) {

        productRepository.deleteById(id);

        return "Delete success";
    }

    // UPDATE
    public Product updateProduct(
            Long id,
            DTKTProductRequestDTO request
    ) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(
                request.getCategoryId()
        ).orElseThrow(() ->
                new RuntimeException("Category not found"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDiscount(request.getDiscount());
        product.setQuantity(request.getQuantity());
        product.setSize(request.getSize());
        product.setColor(request.getColor());
        product.setDescription(request.getDescription());
        product.setCategory(category);

        return productRepository.save(product);
    }

    // SEARCH
    public List<Product> searchProducts(String keyword) {

        return productRepository
                .findByNameContainingIgnoreCase(keyword);
    }

    // FILTER CATEGORY
    public List<Product> getProductsByCategory(
            Long categoryId
    ) {

        return productRepository
                .findByCategoryId(categoryId);
    }


}