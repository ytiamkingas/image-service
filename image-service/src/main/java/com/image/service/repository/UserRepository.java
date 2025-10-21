package com.image.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.image.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}