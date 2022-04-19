package com.jo.user2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostListDto {
    private Long id;
    private Long userId;
    private Long postId;
    private String title;
    private String category;
    private LocalDate createDate;
    private LocalDate editDate;

    public PostListDto(final PostList postList) {
        this.id = postList.getId();
        this.userId = postList.getUserId();
        this.postId = postList.getPostId();
        this.title = postList.getTitle();
        this.category = postList.getCategory();
        this.createDate = postList.getCreateDate();
        this.editDate = postList.getEditDate();
    }

    public static PostList toEntity(final PostListDto postListDto){
        return PostList.builder()
                .id(postListDto.getId())
                .userId(postListDto.getUserId())
                .postId(postListDto.getPostId())
                .title(postListDto.getTitle())
                .category(postListDto.getCategory())
                .createDate(postListDto.getCreateDate())
                .editDate(postListDto.getEditDate())
                .build();}
}
