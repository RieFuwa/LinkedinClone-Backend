package com.bedirhankbts.LinkedinClone.controller.postController;

import com.bedirhankbts.LinkedinClone.dto.companyDto.CompanyGetDto;
import com.bedirhankbts.LinkedinClone.dto.postDto.PostCreateDto;
import com.bedirhankbts.LinkedinClone.dto.postDto.PostGetDto;
import com.bedirhankbts.LinkedinClone.dto.userDto.UserCreateDto;
import com.bedirhankbts.LinkedinClone.model.Post;
import com.bedirhankbts.LinkedinClone.request.postRequest.PostCreateRequest;
import com.bedirhankbts.LinkedinClone.request.userRequest.UserCreateRequest;
import com.bedirhankbts.LinkedinClone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<PostCreateDto> createPost(@RequestBody PostCreateRequest postCreateRequest) {
        return postService.createPost(postCreateRequest);
    }
    @GetMapping("/{postId}")
    public PostGetDto getOnePostByIdWithParameters(@PathVariable("postId")Long postId){
        Post post = postService.getOnePostByIdWithParameters(postId);
        return new PostGetDto(post);
    }
    @GetMapping("/getAll")
    public List<PostGetDto> getAllPost(){
        return postService.getAllPost().stream().map(key-> new PostGetDto(key)).toList();
    }
    @DeleteMapping("/{postId}") //USER ID SINE GORE SILME
    public String deletePostById(@PathVariable("postId") Long postId){
        return postService.deletePostById(postId);
    }
   /* @GetMapping("/getAllUserPost{userId}")
    public List<PostDto> getAllUserPost(@RequestParam Optional<Long> userId){
        return postService.getAllUserPost(userId);
    }

    @GetMapping("/postAnswers{connectedPostId}")
    public List<PostDto> getPostAnswersByPostId(@RequestParam Optional<Long> connectedPostId){
        return postService.getPostAnswersByPostId(connectedPostId);
    }


    @DeleteMapping("/{postId}") //USER ID SINE GORE SILME
    public String deleteUserById(@PathVariable("postId") Long postId){
        return postService.deletePostById(postId);
    }*/
}
