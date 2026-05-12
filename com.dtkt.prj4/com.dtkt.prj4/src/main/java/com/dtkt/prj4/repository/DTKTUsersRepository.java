package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DTKTUsersRepository
        extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
}