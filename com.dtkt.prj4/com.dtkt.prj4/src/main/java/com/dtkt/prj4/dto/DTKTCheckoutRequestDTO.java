package com.dtkt.prj4.dto;

public class DTKTCheckoutRequestDTO {

    private String shippingAddress;

    private String note;

    private String paymentMethod;

    // =========================
    // GETTER SETTER
    // =========================

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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(
            String paymentMethod
    ) {
        this.paymentMethod = paymentMethod;
    }
}