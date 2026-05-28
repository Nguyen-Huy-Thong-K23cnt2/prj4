package com.dtkt.prj4.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // USER
    // =========================
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    // =========================
    // PRODUCT
    // =========================
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // =========================
    // CREATED
    // =========================
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // =========================
    // GETTER SETTER
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt
    ) {
        this.createdAt = createdAt;
    }
}