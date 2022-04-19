package com.jo.user2.service;

import com.jo.user2.model.PostList;
import com.jo.user2.repository.PostListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostListServiceImpl implements PostListService {
    private final PostListRepository postListRepository;

    @Override
    public List<PostList> create(PostList postList) {
        validate(postList);
        postListRepository.save(postList);
        log.warn("유저 PostList Id : {} is saved.", postList.getId());
        return postListRepository.findByUserId(postList.getUserId());
    }

    @Override
    public void validate(PostList postList) {
        if (postList == null) {
            log.warn("값이 비어있습니다.");
            throw new RuntimeException("값이 비어있습니다");
        }

        if (postList.getUserId() == null) {
            log.warn("유저가 없습니다.");
            throw new RuntimeException("Unknown user.");
        }
    }

    @Override
    public List<PostList> retrieve(Long userId) {
        return postListRepository.findByUserId(userId);
    }

    @Override
    public List<PostList> update(PostList postList) {
        validate(postList);
        Optional<PostList> original = postListRepository.findById(postList.getId());
        original.ifPresent(postList1 -> {
            postList1.setId(postList.getId());
            postList1.setUserId(postList.getUserId());
            postList1.setPostId(postList.getPostId());
            postList1.setTitle(postList.getTitle());
            postList1.setCategory(postList.getCategory());
            postList1.setCreateDate(postList.getCreateDate());
            postList1.setEditDate(postList.getEditDate());

            postListRepository.save(postList1);
        });
        return retrieve(postList.getUserId());
    }

    @Override
    public List<PostList> delete(PostList postList) {
        validate(postList);
        try {
            postListRepository.delete(postList);
        } catch (Exception e) {
            log.error("error deleting postList", postList.getId(), e);
            throw new RuntimeException("error deleting postList" + postList.getId());
        }
        return retrieve(postList.getUserId());
    }
}
