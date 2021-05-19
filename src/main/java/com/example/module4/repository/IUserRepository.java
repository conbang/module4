package com.example.module4.repository;

import com.example.module4.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<LoginUser, Long> {
    Optional<LoginUser> findByUsername(String username);
}