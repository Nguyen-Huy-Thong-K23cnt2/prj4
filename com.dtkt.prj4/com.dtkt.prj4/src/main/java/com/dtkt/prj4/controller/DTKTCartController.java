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

        // ADD NEW
        if (!found) {

            DTKTCartItemDTO item =
                    new DTKTCartItemDTO();

            item.setProductId(
                    product.getId()
            );

            item.setName(
                    product.getName()
            );

            item.setPrice(
                    product.getPrice()
            );

            item.setQuantity(1);

            item.setSize(size);

            item.setColor(
                    product.getColor()
            );

            // IMAGE
            if (product.getImages() != null
                    && !product.getImages().isEmpty()) {

                item.setImageUrl(
                        product.getImages()
                                .get(0)
                                .getImageUrl()
                );
            }

            cart.add(item);
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    // VIEW CART
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

    // INCREASE
    @GetMapping("/cart/increase/{id}/{size}")
    public String increaseCart(
            @PathVariable Long id,
            @PathVariable String size,
            HttpSession session
    ) {
        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");
        if (cart != null) {
            for (DTKTCartItemDTO item : cart) {
                if (item.getProductId().equals(id)
                        && item.getSize().equals(size)) {
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


    // DECREASE
    @GetMapping("/cart/decrease/{id}/{size}")
    public String decreaseCart(
            @PathVariable Long id,
            @PathVariable String size,
            HttpSession session
    ) {
        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");
        if (cart != null) {
            DTKTCartItemDTO removeItem = null;
            for (DTKTCartItemDTO item : cart) {
                if (item.getProductId().equals(id)
                        && item.getSize().equals(size)) {
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

    // REMOVE
    @GetMapping("/cart/remove/{id}/{size}")
    public String removeCart(
            @PathVariable Long id,
            @PathVariable String size,
            HttpSession session
    ) {
        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(item ->
                    item.getProductId().equals(id)
                            && item.getSize().equals(size)
            );
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    // CHANGE SIZE
    @PostMapping("/cart/change-size/{id}/{oldSize}")
    public String changeSize(
            @PathVariable Long id,
            @PathVariable String oldSize,
            @RequestParam String size,
            HttpSession session
    ) {
        List<DTKTCartItemDTO> cart =
                (List<DTKTCartItemDTO>)
                        session.getAttribute("cart");
        if (cart != null) {
            for (DTKTCartItemDTO item : cart) {
                if (item.getProductId().equals(id)
                        && item.getSize().equals(oldSize)) {
                    item.setSize(size);
                    break;
                }
            }
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }
}