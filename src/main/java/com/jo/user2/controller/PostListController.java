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
        entity.setId(null);
        entity.setUserId(userId);
        return postListService.create(entity);
    }

    @PutMapping("/editPostList/{userId}/{id}")
    public List<PostList> updatePostList(@PathVariable("userId") Long userId, @PathVariable("id") Long id, @RequestBody PostListDto postListDto) {
        PostList entity = PostListDto.toEntity(postListDto);
        entity.setId(id);
        entity.setUserId(userId);
        return postListService.update(entity);
    }

    @GetMapping("/getPostList/{userId}")
    public List<PostList> getPostList(@PathVariable("userId") Long userId){
        return postListService.retrieve(userId);
    }

    @DeleteMapping("/deletePostList/{userId}/{id}")
    public List<PostList> deletePostList(@PathVariable("userId") Long userId,
                                         @PathVariable("id") Long id,
                                         @RequestBody PostListDto postListDto){
        PostList entity = PostListDto.toEntity(postListDto);
        entity.setId(id);
        entity.setUserId(userId);
        return postListService.delete(entity);
    }


//    @PutMapping("/editPostList")
//    public
}
