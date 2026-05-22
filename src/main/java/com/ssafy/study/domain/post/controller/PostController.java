package com.ssafy.study.domain.post.controller;

import com.ssafy.study.domain.auth.component.SessionManager;
import com.ssafy.study.domain.auth.util.AuthTokenUtils;
import com.ssafy.study.domain.post.controller.dto.PostRequest;
import com.ssafy.study.domain.post.controller.dto.PostResponse;
import com.ssafy.study.domain.post.service.PostService;
import com.ssafy.study.global.exception.CustomException;
import com.ssafy.study.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.study.domain.auth.util.AuthTokenUtils.parseBearerToken;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService ps;
    private final SessionManager sessionManager;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> response = ps.getAllPosts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        PostResponse response = ps.findById(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request, @RequestHeader("Authorization") String bearerToken) {
        String token = parseBearerToken(bearerToken); // exception은 util 안에서 던지게 했습니다
        Long memberId = sessionManager.getMemberId(token);
        PostResponse response = ps.save(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest, @RequestHeader("Authorization") String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        
        String token = parseBearerToken(bearerToken);

        Long memberId = sessionManager.getMemberId(token);
        PostResponse response = ps.update(postId, postRequest, memberId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @RequestHeader("Authorization") String bearerToken) {
        String token = parseBearerToken(bearerToken);
        Long memberId = sessionManager.getMemberId(token);
        ps.delete(postId, memberId);
        return ResponseEntity.noContent().build();
    }


}
