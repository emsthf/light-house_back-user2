package com.jo.user2.controller;

import com.jo.user2.model.PostList;
import com.jo.user2.model.PostListDto;
import com.jo.user2.service.PostListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostListController {
    private final PostListService postListService;

    @PostMapping("/addPostList/{userId}")
    public List<PostList> createPostList(@PathVariable("userId") Long userId, @RequestBody PostListDto postListDto) {
        PostList entity = PostListDto.toEntity(postListDto);
        return postListService.create(entity);
    }

//    @PutMapping("/editPostList")
//    public
}
