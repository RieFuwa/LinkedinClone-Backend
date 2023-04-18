package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.postDto.PostCreateDto;
import com.bedirhankbts.LinkedinClone.dto.postDto.PostGetDto;
import com.bedirhankbts.LinkedinClone.model.Post;
import com.bedirhankbts.LinkedinClone.request.postRequest.PostCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
    ResponseEntity<PostCreateDto> createPost(PostCreateRequest postCreateRequest);
    Post getPostById(Long postId);

    PostGetDto getOnePostByIdWithParameters(Long postId);

    List<PostGetDto> getAllPost();

    String deletePostById(Long postId);

    List<PostGetDto> getPostAnswersByPostId(Optional<Long> postId);

    List<PostGetDto> getAllUserPost(Optional<Long> userId);
}
