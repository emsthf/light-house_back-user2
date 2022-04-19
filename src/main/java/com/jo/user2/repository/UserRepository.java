package com.jo.user2.repository;

import com.jo.user2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
    Boolean existsByEmail(String email);
//    List<User> findByEmailOrPasswordContaining(String keyword);
}
