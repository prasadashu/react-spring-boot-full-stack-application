package com.commerce.commerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.commerce.commerceapplication.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
