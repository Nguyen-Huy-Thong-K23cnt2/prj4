package com.dtkt.prj4.controller;

import com.dtkt.prj4.entity.Product;
import com.dtkt.prj4.entity.ProductComment;
import com.dtkt.prj4.entity.Users;
import com.dtkt.prj4.repository.DTKTProductCommentRepository;
import com.dtkt.prj4.service.DTKTProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class DTKTProductCommentController {
    private final DTKTProductCommentRepository commentRepository;

    private final DTKTProductService productService;

    public DTKTProductCommentController(
            DTKTProductCommentRepository commentRepository,
            DTKTProductService productService
    ) {

        this.commentRepository = commentRepository;
        this.productService = productService;
    }

    @PostMapping("/product/comment/{id}")
    public String addComment(
            @PathVariable Long id,
            @RequestParam Integer star,
            @RequestParam String content,
            HttpSession session
    ) {

        Users user =
                (Users) session.getAttribute(
                        "loggedInUser"
                );

        if (user == null) {

            return "redirect:/login";
        }

        Product product =
                productService.getProductById(id);

        ProductComment comment =
                new ProductComment();

        comment.setProduct(product);

        comment.setUser(user);

        comment.setStar(star);

        comment.setContent(content);

        comment.setIsVisible(true);

        comment.setCreatedAt(
                LocalDateTime.now()
        );

        commentRepository.save(comment);

        return "redirect:/product/" + id;
    }
}
