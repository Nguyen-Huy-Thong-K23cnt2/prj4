package com.dtkt.prj4.controller;

import com.dtkt.prj4.dto.DTKTCartItemDTO;
import com.dtkt.prj4.dto.DTKTCheckoutRequestDTO;
import com.dtkt.prj4.entity.Order;
import com.dtkt.prj4.entity.Users;
import com.dtkt.prj4.service.DTKTOrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DTKTCheckoutController {

    private final DTKTOrderService orderService;

    public DTKTCheckoutController(
            DTKTOrderService orderService
    ) {

        this.orderService = orderService;
    }

    // =========================
    // CHECKOUT PAGE
    // =========================
    @GetMapping("/checkout")
    public String checkoutPage(
            HttpSession session,
            Model model
    ) {

        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");

        if (cart == null) {

            cart = new ArrayList<>();
        }

        double total = 0;

        for (DTKTCartItemDTO item : cart) {

            total += item.getPrice()
                    * item.getQuantity();
        }

        model.addAttribute("cart", cart);

        model.addAttribute("total", total);

        model.addAttribute(
                "checkoutRequest",
                new DTKTCheckoutRequestDTO()
        );

        return "user/checkout";
    }

    // =========================
    // PLACE ORDER
    // =========================
    @PostMapping("/checkout")
    public String placeOrder(
            @ModelAttribute
            DTKTCheckoutRequestDTO request,

            HttpSession session
    ) {

        Users user =
                (Users)
                        session.getAttribute(
                                "loggedInUser"
                        );

        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");

        if (cart == null
                || cart.isEmpty()) {

            return "redirect:/cart";
        }

        Order order =
                orderService.createOrder(
                        user,
                        cart,
                        request
                );

        // CLEAR CART
        session.removeAttribute("cart");

        return "redirect:/order-success/"
                + order.getId();
    }

    // =========================
    // SUCCESS PAGE
    // =========================
    @GetMapping("/order-success/{id}")
    public String orderSuccess(
            Model model
    ) {

        return "user/order-success";
    }
}