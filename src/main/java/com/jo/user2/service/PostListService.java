package com.jo.user2.service;

import com.jo.user2.model.PostList;

import java.util.List;

public interface PostListService {
    List<PostList> create(PostList postList);
    void validate(PostList postList);
    List<PostList> retrieve(Long uerId);
    List<PostList> update(PostList postList);
    List<PostList> delete(PostList postList);
}
