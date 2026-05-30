package com.dtkt.prj4.controller;
import com.dtkt.prj4.entity.Order;
import com.dtkt.prj4.entity.Users;
import com.dtkt.prj4.repository.DTKTOrderRepository;
import com.dtkt.prj4.repository.DTKTUserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DTKTProfileController {
    private final DTKTUserRepository userRepository;
    private final DTKTOrderRepository orderRepository;
    public DTKTProfileController(
            DTKTUserRepository userRepository,
            DTKTOrderRepository orderRepository
    ) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }
    
    // PROFILE PAGE
    @GetMapping("/profile")
    public String profilePage(
            HttpSession session,
            Model model
    ) {
        Users user =
                (Users) session.getAttribute(
                        "loggedInUser"
                );
        if (user == null) {
            return "redirect:/user/login";
        }
        model.addAttribute(
                "user",
                user
        );
        return "user/profile";
    }
    // UPDATE PROFILE
    @PostMapping("/profile")
    public String updateProfile(
            @ModelAttribute Users request,
            HttpSession session
    ) {
        Users user =
                (Users) session.getAttribute(
                        "loggedInUser"
                );

        if (user == null) {
            return "redirect:/user/login";
        }
        user.setFullName(
                request.getFullName()
        );
        user.setPhone(
                request.getPhone()
        );
        user.setAddress(
                request.getAddress()
        );
        user.setAvatar(
                request.getAvatar()
        );
        userRepository.save(user);
        session.setAttribute(
                "loggedInUser",
                user
        );

        return "redirect:/profile";
    }
    
    // MY ORDERS
    @GetMapping("/my-orders")
    public String myOrders(
            HttpSession session,
            Model model
    ) {
        Users user =
                (Users) session.getAttribute(
                        "loggedInUser"
                );
        if (user == null) {

            return "redirect:/user/login";
        }
        model.addAttribute(
                "orders",
                orderRepository.findByUserId(
                        user.getId()
                )
        );
        return "user/my-orders";
    }
    @GetMapping("/my-orders/{id}")
    public String orderDetail(
            @PathVariable Long id,
            HttpSession session,
            Model model
    ) {

        Users user =
                (Users) session.getAttribute(
                        "loggedInUser"
                );

        if (user == null) {

            return "redirect:/user/login";
        }
        Order order =
                orderRepository
                        .findByIdAndUserId(
                                id,
                                user.getId()
                        )
                        .orElseThrow();
        model.addAttribute(
                "order",
                order
        );
        return "user/order-detail";
    }
}