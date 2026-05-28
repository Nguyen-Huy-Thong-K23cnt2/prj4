package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DTKTWishlistRepository
        extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUserId(
            Long userId
    );

    boolean existsByUserIdAndProductId(
            Long userId,
            Long productId
    );

    void deleteByUserIdAndProductId(
            Long userId,
            Long productId
    );
}