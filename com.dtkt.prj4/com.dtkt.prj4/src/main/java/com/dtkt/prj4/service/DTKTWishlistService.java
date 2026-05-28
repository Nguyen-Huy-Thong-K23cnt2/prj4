package com.dtkt.prj4.service;

import com.dtkt.prj4.entity.Product;
import com.dtkt.prj4.entity.Users;
import com.dtkt.prj4.entity.Wishlist;
import com.dtkt.prj4.repository.DTKTWishlistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DTKTWishlistService {

    private final DTKTWishlistRepository
            wishlistRepository;

    public DTKTWishlistService(
            DTKTWishlistRepository wishlistRepository
    ) {

        this.wishlistRepository =
                wishlistRepository;
    }

    // =========================
    // GET USER WISHLIST
    // =========================
    public List<Wishlist> getWishlistByUser(
            Long userId
    ) {

        return wishlistRepository
                .findByUserId(userId);
    }

    // =========================
    // ADD WISHLIST
    // =========================
    public void addWishlist(
            Users user,
            Product product
    ) {

        boolean exists =
                wishlistRepository
                        .existsByUserIdAndProductId(
                                user.getId(),
                                product.getId()
                        );

        if (!exists) {

            Wishlist wishlist =
                    new Wishlist();

            wishlist.setUser(user);

            wishlist.setProduct(product);

            wishlist.setCreatedAt(
                    LocalDateTime.now()
            );

            wishlistRepository.save(wishlist);
        }
    }

    // =========================
    // REMOVE WISHLIST
    // =========================
    public void removeWishlist(
            Long userId,
            Long productId
    ) {

        wishlistRepository
                .deleteByUserIdAndProductId(
                        userId,
                        productId
                );
    }
}