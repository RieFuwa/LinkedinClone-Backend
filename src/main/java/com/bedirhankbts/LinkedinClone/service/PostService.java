package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.postDto.PostCreateDto;
import com.bedirhankbts.LinkedinClone.model.Post;
import com.bedirhankbts.LinkedinClone.request.postRequest.PostCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    ResponseEntity<PostCreateDto> createPost(PostCreateRequest postCreateRequest);

    Post getOnePostByIdWithParameters(Long postId);

    List<Post> getAllPost();

    String deletePostById(Long postId);
}
