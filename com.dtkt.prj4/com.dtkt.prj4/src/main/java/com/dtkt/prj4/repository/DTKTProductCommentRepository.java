package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DTKTProductCommentRepository
        extends JpaRepository<ProductComment, Long> {

    List<ProductComment> findByProductIdAndIsVisibleTrue(
            Long productId
    );
}
