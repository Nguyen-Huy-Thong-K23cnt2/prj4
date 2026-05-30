package com.dtkt.prj4.controller;

import com.dtkt.prj4.entity.Role;
import com.dtkt.prj4.entity.Users;
import com.dtkt.prj4.repository.DTKTRoleRepository;
import com.dtkt.prj4.repository.DTKTUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class DTKTAdminUsersController {

    private final DTKTUserRepository userRepository;

    private final DTKTRoleRepository roleRepository;

    public DTKTAdminUsersController(
            DTKTUserRepository userRepository,
            DTKTRoleRepository roleRepository
    ) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String usersPage(
            Model model
    ) {

        model.addAttribute(
                "users",
                userRepository.findAll()
        );

        return "admin/users";
    }

    @GetMapping("/toggle/{id}")
    public String toggleUser(
            @PathVariable Long id
    ) {

        Users user =
                userRepository.findById(id)
                        .orElseThrow();
        user.setIsActive(
                !user.getIsActive()
        );
        userRepository.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/role/{id}")
    public String changeRole(
            @PathVariable Long id
    ) {
        Users user =
                userRepository.findById(id)
                        .orElseThrow();
        if ("USER".equals(
                user.getRole().getName()
        )) {
            Role adminRole =
                    roleRepository
                            .findByName("ADMIN")
                            .orElseThrow();
            user.setRole(adminRole);
        } else {
            Role userRole =
                    roleRepository
                            .findByName("USER")
                            .orElseThrow();
            user.setRole(userRole);
        }
        userRepository.save(user);
        return "redirect:/admin/users";
    }
}