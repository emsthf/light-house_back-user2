package com.jo.user2.repository;

import com.jo.user2.model.PostList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostListRepository extends JpaRepository<PostList, Long> {
    List<PostList> findByUserId(Long userId);
}
