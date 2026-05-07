package com.ssafy.study.post.repository;

import com.ssafy.study.post.entity.PostEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final List<PostEntity> postDB = new ArrayList<>();

    public PostEntity save(PostEntity postEntity) {
        postDB.add(postEntity);
        return postEntity;
    }

    public Optional<PostEntity> findById(Long postId) {
        for (PostEntity post : postDB) {
            if (postId.equals(post.getId())) return Optional.of(post);
        }
        return Optional.empty();
    }

    public List<PostEntity> findAll() {
        return postDB;
    }

    public void deleteById(Long postId) {
        postDB.removeIf(post -> postId.equals(post.getId()));
    }

}
