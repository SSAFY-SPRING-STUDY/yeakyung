package com.ssafy.study.post.repository;

import com.ssafy.study.post.controller.dto.PostRequest;
import com.ssafy.study.post.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final List<PostEntity> postList = new ArrayList<>();

    public PostEntity save(PostEntity postEntity) {
        postList.add(postEntity);
        return postEntity;
    }

    public Optional<PostEntity> findById(Long postId) {
        for (PostEntity post : postList) {
            if (postId.equals(post.getId())) return Optional.of(post);
        }
        return Optional.empty();
    }

    public List<PostEntity> findAll() {
        return new ArrayList<>(postList);
    }

    public void deleteById(Long postId) {
        postList.removeIf(post -> postId.equals(post.getId()));
    }

    public Optional<PostEntity> update(Long postId, String title, String content) {
        for (PostEntity post : postList) {
            if (postId.equals(post.getId())) {
                post.update(title, content);
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }


}
