package com.dtkt.prj4.controller;

import com.dtkt.prj4.dto.DTKTRegisterRequestDTO;
import com.dtkt.prj4.entity.Users;
import com.dtkt.prj4.service.DTKTAuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DTKTAuthController {

    private final DTKTAuthService authService;

    public DTKTAuthController(
            DTKTAuthService authService
    ) {
        this.authService = authService;
    }
    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage() {

        return "user/login";
    }

    // REGISTER PAGE
    @GetMapping("/register")
    public String registerPage(
            Model model
    ) {

        model.addAttribute(
                "registerRequest",
                new DTKTRegisterRequestDTO()
        );

        return "user/register";
    }

    // REGISTER
    @PostMapping("/register")
    public String register(
            @ModelAttribute
            DTKTRegisterRequestDTO request,
            Model model
    ) {
        String result =
                authService.register(request);
        model.addAttribute(
                "message",
                result
        );
        return "redirect:/login";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {

        Users user =
                authService.login(
                        username,
                        password
                );

        if (user == null) {

            model.addAttribute(
                    "error",
                    "Sai tài khoản hoặc mật khẩu"
            );

            return "user/login";
        }

        session.setAttribute(
                "loggedInUser",
                user
        );

        // ADMIN
        if (user.getRole()
                .getName()
                .equals("ADMIN")) {
            return "redirect:/admin";
        }

        // USER
        return "redirect:/";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(
            HttpSession session
    ) {

        session.invalidate();

        return "redirect:/";
    }
}