package com.ssafy.study.post.service;

import com.ssafy.study.post.controller.dto.PostRequest;
import com.ssafy.study.post.controller.dto.PostResponse;
import com.ssafy.study.post.entity.PostEntity;
import com.ssafy.study.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponse save(PostRequest postRequest) {
        PostEntity savedEntity = postRepository.save(postRequest.toEntity());
        return PostResponse.from(savedEntity);
    }

    public List<PostResponse> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        List<PostResponse> postResponses = new ArrayList<>();
        for (PostEntity entity : postEntities) {
            postResponses.add(PostResponse.from(entity));
        }
        return postResponses;
    }

    public PostResponse findById(Long postId) {
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));
        return PostResponse.from(entity);
    }

    public PostResponse update(Long postId, PostRequest postRequest) {
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));
        entity.update(postRequest.getTitle(), postRequest.getContent());
        return PostResponse.from(entity);
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

}
