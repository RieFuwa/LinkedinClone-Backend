package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.postDto.PostCreateDto;
import com.bedirhankbts.LinkedinClone.model.Post;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.repository.PostRepository;
import com.bedirhankbts.LinkedinClone.request.postRequest.PostCreateRequest;
import com.bedirhankbts.LinkedinClone.service.PostService;
import com.bedirhankbts.LinkedinClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;
    @Override
    public ResponseEntity<PostCreateDto> createPost(PostCreateRequest postCreateRequest) {
        PostCreateDto postCreateDto = new PostCreateDto();
        User user = userService.getUserById(postCreateRequest.getUserId());
        if(user==null){
            postCreateDto.setMessage("Post not created. UserId not found.");
            return new ResponseEntity<>(postCreateDto, HttpStatus.BAD_REQUEST);
        }
        Post toCreate = new Post();
        toCreate.setId(postCreateRequest.getId());
        toCreate.setUser(user);
        if(postCreateRequest.getConnectedPostId()!=null){
            toCreate.setConnectedPost(getOnePostByIdWithParameters(postCreateRequest.getConnectedPostId()));
        }
        toCreate.setCreateDate(new Date());
        toCreate.setPostTitle(postCreateRequest.getPostTitle());
        toCreate.setPostText(postCreateRequest.getPostText());
        postRepository.save(toCreate);
        postCreateDto.setMessage("Post successfully created.");
        postCreateDto.setPostId(toCreate.getId());
        postCreateDto.setUserId(toCreate.getUser().getId());
        return new ResponseEntity<>(postCreateDto,HttpStatus.CREATED);
    }

    @Override
    public Post getOnePostByIdWithParameters(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public String deletePostById(Long postId) {
        if (!postRepository.existsById(postId)) {
            return "Post with id not found " +postId+".";
        }
        postRepository.deleteById(postId);
        return "Post with id " +postId+ " has been deleted success.";
    }


}
