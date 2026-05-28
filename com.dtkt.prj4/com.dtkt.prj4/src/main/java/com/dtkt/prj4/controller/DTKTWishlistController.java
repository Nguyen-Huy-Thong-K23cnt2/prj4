package com.dtkt.prj4.controller;

import com.dtkt.prj4.entity.Users;
import com.dtkt.prj4.service.DTKTProductService;
import com.dtkt.prj4.service.DTKTWishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DTKTWishlistController {

    private final DTKTWishlistService
            wishlistService;

    private final DTKTProductService
            productService;

    public DTKTWishlistController(
            DTKTWishlistService wishlistService,
            DTKTProductService productService
    ) {

        this.wishlistService =
                wishlistService;

        this.productService =
                productService;
    }

    // =========================
    // WISHLIST PAGE
    // =========================
    @GetMapping("/wishlist")
    public String wishlistPage(
            HttpSession session,
            Model model
    ) {

        Users user =
                (Users)
                        session.getAttribute(
                                "loggedInUser"
                        );

        if (user == null) {

            return "redirect:/user/login";
        }

        model.addAttribute(
                "wishlists",
                wishlistService.getWishlistByUser(
                        user.getId()
                )
        );

        return "user/wishlist";
    }

    // =========================
    // ADD WISHLIST
    // =========================
    @GetMapping("/wishlist/add/{id}")
    public String addWishlist(
            @PathVariable Long id,
            HttpSession session
    ) {

        Users user =
                (Users)
                        session.getAttribute(
                                "loggedInUser"
                        );

        if (user == null) {

            return "redirect:/user/login";
        }

        wishlistService.addWishlist(
                user,
                productService.getProductById(id)
        );

        return "redirect:/wishlist";
    }

    // =========================
    // REMOVE WISHLIST
    // =========================
    @GetMapping("/wishlist/remove/{id}")
    public String removeWishlist(
            @PathVariable Long id,
            HttpSession session
    ) {

        Users user =
                (Users)
                        session.getAttribute(
                                "loggedInUser"
                        );

        if (user != null) {

            wishlistService.removeWishlist(
                    user.getId(),
                    id
            );
        }

        return "redirect:/wishlist";
    }
}