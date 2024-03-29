package com.bedirhankbts.LinkedinClone.repository;

import com.bedirhankbts.LinkedinClone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);

    List<Post> findByConnectedPostId(Long postId);

    Long countByUserId(Long userId);
}
