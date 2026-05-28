package com.dtkt.prj4.service;

import com.dtkt.prj4.dto.DTKTCartItemDTO;
import com.dtkt.prj4.dto.DTKTCheckoutRequestDTO;
import com.dtkt.prj4.entity.*;
import com.dtkt.prj4.repository.DTKTOrderDetailRepository;
import com.dtkt.prj4.repository.DTKTOrderRepository;
import com.dtkt.prj4.repository.DTKTPaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DTKTOrderService {

    private final DTKTOrderRepository orderRepository;

    private final DTKTOrderDetailRepository
            orderDetailRepository;

    private final DTKTPaymentRepository
            paymentRepository;

    public DTKTOrderService(
            DTKTOrderRepository orderRepository,
            DTKTOrderDetailRepository orderDetailRepository,
            DTKTPaymentRepository paymentRepository
    ) {

        this.orderRepository = orderRepository;

        this.orderDetailRepository =
                orderDetailRepository;

        this.paymentRepository =
                paymentRepository;
    }

    // =========================
    // CREATE ORDER
    // =========================
    public Order createOrder(
            Users user,
            List<DTKTCartItemDTO> cart,
            DTKTCheckoutRequestDTO request
    ) {

        // TOTAL
        double total = 0;

        for (DTKTCartItemDTO item : cart) {

            total += item.getPrice()
                    * item.getQuantity();
        }

        // ORDER
        Order order = new Order();

        order.setUser(user);

        order.setTotalPrice(total);

        order.setDiscountValue(0.0);

        order.setFinalPrice(total);

        order.setStatus("PENDING");

        order.setShippingAddress(
                request.getShippingAddress()
        );

        order.setNote(
                request.getNote()
        );

        order.setCreatedAt(
                LocalDateTime.now()
        );

        Order savedOrder =
                orderRepository.save(order);

        // ORDER DETAIL
        for (DTKTCartItemDTO item : cart) {

            OrderDetail detail =
                    new OrderDetail();

            Product product =
                    new Product();

            product.setId(
                    item.getProductId()
            );

            detail.setOrder(savedOrder);

            detail.setProduct(product);

            detail.setQuantity(
                    item.getQuantity()
            );

            detail.setPrice(
                    item.getPrice()
            );

            detail.setSize(
                    item.getSize()
            );

            detail.setColor(
                    item.getColor()
            );

            orderDetailRepository.save(detail);
        }

        // PAYMENT
        Payment payment =
                new Payment();

        payment.setOrder(savedOrder);

        payment.setMethod(
                request.getPaymentMethod()
        );

        payment.setStatus("PENDING");

        payment.setAmount(total);

        payment.setCreatedAt(
                LocalDateTime.now()
        );

        paymentRepository.save(payment);

        return savedOrder;
    }
}