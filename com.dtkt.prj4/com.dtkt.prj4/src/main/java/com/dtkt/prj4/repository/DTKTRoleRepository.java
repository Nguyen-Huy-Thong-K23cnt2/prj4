package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DTKTRoleRepository
        extends JpaRepository<Role, Long> {

}