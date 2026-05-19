package com.dtkt.prj4.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_main")
    private Boolean isMain;

    // =========================
    // PRODUCT
    // =========================
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // =========================
    // GETTER SETTER
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(Boolean main) {
        isMain = main;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}