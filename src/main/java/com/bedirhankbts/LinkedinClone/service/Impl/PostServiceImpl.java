package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.dto.postDto.PostCreateDto;
import com.bedirhankbts.LinkedinClone.dto.postDto.PostGetDto;
import com.bedirhankbts.LinkedinClone.dto.reportDto.ReportDto;
import com.bedirhankbts.LinkedinClone.model.Post;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.repository.PostRepository;
import com.bedirhankbts.LinkedinClone.request.postRequest.PostCreateRequest;
import com.bedirhankbts.LinkedinClone.service.LikeService;
import com.bedirhankbts.LinkedinClone.service.PostService;
import com.bedirhankbts.LinkedinClone.service.ReportService;
import com.bedirhankbts.LinkedinClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private ReportService reportService;

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
            toCreate.setConnectedPost(getPostById(postCreateRequest.getConnectedPostId()));
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
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse( null);
    }

    @Override
    public PostGetDto getOnePostByIdWithParameters(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        List<LikeDto> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null),Optional.of(postId));
        List<ReportDto> reports = reportService.getAllPostReports(Optional.of(postId));

        return new PostGetDto(post,likes,reports);    }

    @Override
    public List<PostGetDto> getAllPost() {
        List<Post> post;

        post = postRepository.findAll();
        return post.stream().map(p -> {
            List<LikeDto> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
            List<ReportDto> reports = reportService.getAllPostReports(Optional.of(p.getId()));

            return new PostGetDto(p, likes,reports);}).collect(Collectors.toList());
    }

    @Override
    public String deletePostById(Long postId) {
        if (!postRepository.existsById(postId)) {
            return "Post with id not found " +postId+".";
        }
        postRepository.deleteById(postId);
        return "Post with id " +postId+ " has been deleted success.";
    }

    @Override
    public List<PostGetDto> getPostAnswersByPostId(Optional<Long> connectedPostId) {
        List<Post> posts;
        if(connectedPostId.isPresent()){
            posts=postRepository.findByConnectedPostId(connectedPostId.get());
        }else
            posts= postRepository.findAll();
        return posts.stream().map(p -> {
            List<LikeDto> likes = likeService.getAllLikesWithParam( Optional.ofNullable(null), Optional.of(p.getId()));
            List<ReportDto> reports = reportService.getAllPostReports(Optional.of(p.getId()));

            return new PostGetDto(p, likes,reports);}).collect(Collectors.toList());
    }

    @Override
    public List<PostGetDto> getAllUserPost(Optional<Long> userId) {

        List<Post> post;
        if(userId.isPresent()){
            post=postRepository.findByUserId(userId.get());
        }else
            post = postRepository.findAll();
        return post.stream().map(p -> {
            List<LikeDto> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
            List<ReportDto> reports = reportService.getAllPostReports(Optional.of(p.getId()));

            return new PostGetDto(p, likes,reports);}).collect(Collectors.toList());
    }

    @Override
    public Long getCountPostByUserId(Long userId) {
        return postRepository.countByUserId(userId);
    }


}
