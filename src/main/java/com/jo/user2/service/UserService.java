package com.jo.user2.service;

import com.jo.user2.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);
    User getUser(final String email, final String password);
    User update(final User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUser();
    List<User> searchUsers(String keyword);
}
