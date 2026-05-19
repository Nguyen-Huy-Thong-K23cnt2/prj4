package com.dtkt.prj4.controller;

import com.dtkt.prj4.dto.DTKTCartItemDTO;
import com.dtkt.prj4.entity.Product;
import com.dtkt.prj4.service.DTKTProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DTKTCartController {

    private final DTKTProductService productService;

    public DTKTCartController(
            DTKTProductService productService
    ) {
        this.productService = productService;
    }

    // =========================
    // ADD TO CART
    // =========================
    @PostMapping("/cart/add/{id}")
    public String addToCart(
            @PathVariable Long id,
            @RequestParam String size,
            HttpSession session
    ) {

        Product product =
                productService.getProductById(id);

        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");

        if (cart == null) {

            cart = new ArrayList<>();
        }

        boolean found = false;

        for (DTKTCartItemDTO item : cart) {

            if (item.getProductId().equals(id)
                    && item.getSize().equals(size)) {

                item.setQuantity(
                        item.getQuantity() + 1
                );

                found = true;

                break;
            }
        }

        // nếu chưa có thì thêm mới
        if (!found) {

            DTKTCartItemDTO item =
                    new DTKTCartItemDTO();

            item.setProductId(product.getId());

            item.setName(
                    product.getName()
            );

            item.setPrice(
                    product.getPrice()
            );

            item.setQuantity(1);

            item.setSize(size);

            cart.add(item);
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    // =========================
    // VIEW CART
    // =========================
    @GetMapping("/cart")
    public String viewCart(
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

        return "user/cart";
    }

    // =========================
    // INCREASE
    // =========================
    @GetMapping("/cart/increase/{id}")
    public String increaseCart(
            @PathVariable Long id,
            HttpSession session
    ) {

        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");

        if (cart != null) {

            for (DTKTCartItemDTO item : cart) {

                if (item.getProductId().equals(id)) {

                    item.setQuantity(
                            item.getQuantity() + 1
                    );

                    break;
                }
            }
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    // =========================
    // DECREASE
    // =========================
    @GetMapping("/cart/decrease/{id}")
    public String decreaseCart(
            @PathVariable Long id,
            HttpSession session
    ) {

        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");

        if (cart != null) {

            DTKTCartItemDTO removeItem = null;

            for (DTKTCartItemDTO item : cart) {

                if (item.getProductId().equals(id)) {

                    if (item.getQuantity() > 1) {

                        item.setQuantity(
                                item.getQuantity() - 1
                        );

                    } else {

                        removeItem = item;
                    }

                    break;
                }
            }

            if (removeItem != null) {

                cart.remove(removeItem);
            }
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    // =========================
    // REMOVE
    // =========================
    @GetMapping("/cart/remove/{id}")
    public String removeCart(
            @PathVariable Long id,
            HttpSession session
    ) {

        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");

        if (cart != null) {

            cart.removeIf(item ->
                    item.getProductId().equals(id));
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    // =========================
    // CHANGE SIZE
    // =========================
    @PostMapping("/cart/change-size/{id}")
    public String changeSize(
            @PathVariable Long id,
            @RequestParam String size,
            HttpSession session
    ) {

        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");

        if (cart != null) {

            for (DTKTCartItemDTO item : cart) {

                if (item.getProductId().equals(id)) {

                    item.setSize(size);

                    break;
                }
            }
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }
}