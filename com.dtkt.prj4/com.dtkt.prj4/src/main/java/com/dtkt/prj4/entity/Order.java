package com.dtkt.prj4.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // USER
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    // TOTAL
    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "discount_value")
    private Double discountValue;

    @Column(name = "final_price")
    private Double finalPrice;

    // STATUS
    private String status;

    // SHIPPING
    @Column(name = "shipping_address")
    private String shippingAddress;
    private String note;

    // CREATED
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ORDER DETAIL
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    // GETTER SETTER
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(
            String shippingAddress
    ) {
        this.shippingAddress = shippingAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt
    ) {
        this.createdAt = createdAt;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(
            List<OrderDetail> orderDetails
    ) {
        this.orderDetails = orderDetails;
    }
}