package com.ssafy.study.domain.post.service;

import com.ssafy.study.domain.member.entity.MemberEntity;
import com.ssafy.study.domain.member.repository.MemberRepository;
import com.ssafy.study.domain.post.controller.dto.PostRequest;
import com.ssafy.study.domain.post.controller.dto.PostResponse;
import com.ssafy.study.domain.post.entity.PostEntity;
import com.ssafy.study.domain.post.repository.PostRepository;
import com.ssafy.study.global.exception.CustomException;
import com.ssafy.study.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostResponse save(PostRequest postRequest, Long authorId) {
        MemberEntity member = memberRepository.findById(authorId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity savedEntity = postRepository.save(postRequest.toEntity(member));
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
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.from(post);
    }

    public PostResponse update(Long postId, PostRequest postRequest, Long authorId) {
        memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!post.getAuthor().getId().equals(authorId)) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
        post.update(postRequest.getTitle(), postRequest.getContent());
        return PostResponse.from(post);
    }

    public void delete(Long postId, Long authorId) {
        memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!post.getAuthor().getId().equals(authorId)) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
        postRepository.deleteById(postId);
    }

}
