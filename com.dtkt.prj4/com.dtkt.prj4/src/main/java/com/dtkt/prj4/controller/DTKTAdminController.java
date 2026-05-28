package com.dtkt.prj4.controller;

import com.dtkt.prj4.repository.DTKTOrderRepository;
import com.dtkt.prj4.repository.DTKTProductRepository;
import com.dtkt.prj4.repository.DTKTUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DTKTAdminController {

    private final DTKTProductRepository
            productRepository;

    private final DTKTOrderRepository
            orderRepository;

    private final DTKTUserRepository
            userRepository;

    public DTKTAdminController(
            DTKTProductRepository productRepository,
            DTKTOrderRepository orderRepository,
            DTKTUserRepository userRepository
    ) {

        this.productRepository =
                productRepository;

        this.orderRepository =
                orderRepository;

        this.userRepository =
                userRepository;
    }

    @GetMapping("/admin")
    public String adminDashboard(
            Model model
    ) {

        long totalProducts =
                productRepository.count();

        long totalOrders =
                orderRepository.count();

        long totalUsers =
                userRepository.count();

        double revenue = orderRepository.findAll()
                .stream()
                .mapToDouble(order ->

                        order.getFinalPrice() != null
                                ? order.getFinalPrice()
                                : 0

                ).sum();

        model.addAttribute(
                "totalProducts",
                totalProducts
        );

        model.addAttribute(
                "totalOrders",
                totalOrders
        );

        model.addAttribute(
                "totalUsers",
                totalUsers
        );

        model.addAttribute(
                "revenue",
                revenue
        );

        return "admin/dashboard";
    }
}