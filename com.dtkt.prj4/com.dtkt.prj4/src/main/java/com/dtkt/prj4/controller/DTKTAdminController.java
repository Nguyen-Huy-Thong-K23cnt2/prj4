package com.dtkt.prj4.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DTKTAdminController {

    @GetMapping("/admin")
    public String adminDashboard(
            HttpSession session
    ) {

        Object user =
                session.getAttribute(
                        "loggedInUser"
                );

        if (user == null) {

            return "redirect:/login";
        }

        return "admin/dashboard";
    }
}