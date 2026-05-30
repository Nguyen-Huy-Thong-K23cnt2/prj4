package com.dtkt.prj4.controller;

import com.dtkt.prj4.entity.Order;
import com.dtkt.prj4.repository.DTKTOrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/orders")
public class DTKTAdminOrderController {
    private final DTKTOrderRepository orderRepository;
    public DTKTAdminOrderController(
            DTKTOrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }
    // ORDER PAGE
    @GetMapping
    public String orderPage(
            Model model
    ) {
        model.addAttribute(
                "orders",
                orderRepository.findAll()
        );
        return "admin/orders";
    }
    // UPDATE STATUS
    @GetMapping("/status/{id}")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String value
    ) {
        var order =
                orderRepository.findById(id)
                        .orElseThrow();
        order.setStatus(value);
        orderRepository.save(order);
        return "redirect:/admin/orders";
    }
    @GetMapping("/{id}")
    public String orderDetail(
            @PathVariable Long id,
            Model model
    ) {
        Order order =
                orderRepository
                        .findById(id)
                        .orElseThrow();
        model.addAttribute(
                "order",
                order
        );
        return "admin/order-detail";
    }
}