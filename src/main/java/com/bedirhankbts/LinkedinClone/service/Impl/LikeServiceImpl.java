package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.dto.postDto.PostGetDto;
import com.bedirhankbts.LinkedinClone.model.Like;
import com.bedirhankbts.LinkedinClone.model.Post;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.repository.LikeRepository;
import com.bedirhankbts.LinkedinClone.request.likeRequest.LikeCreateRequest;
import com.bedirhankbts.LinkedinClone.service.LikeService;
import com.bedirhankbts.LinkedinClone.service.PostService;
import com.bedirhankbts.LinkedinClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Override
    public List<LikeDto> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else
            list = likeRepository.findAll();
        return list.stream().map(like-> new LikeDto(like)).collect(Collectors.toList());
    }

    @Override
    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    @Override
    public ResponseEntity<LikeDto> createLike(LikeCreateRequest newLike) {
        Post post = postService.getPostById(newLike.getPostId());
        User user = userService.getUserById(newLike.getUserId());
        PostGetDto postDto = postService.getOnePostByIdWithParameters(newLike.getPostId());
        if(user != null && post != null && postDto.getLikeList().stream().filter(key-> Objects.equals(key.getUserId(),user.getId())).collect(Collectors.toList()).isEmpty() )
        {
            Like toCreate = new Like();
            toCreate.setId(newLike.getId());
            toCreate.setUser(user);
            toCreate.setPost(post);
            likeRepository.save(toCreate);
            LikeDto likeDto = new LikeDto(toCreate);
            return  new ResponseEntity<>(likeDto, HttpStatus.CREATED);
        }else
            return null;
    }

    @Override
    public String deleteLikeById(Long likeId) {
        if (!likeRepository.existsById(likeId)) {
            return "Like with id not found " +likeId+".";
        }
        likeRepository.deleteById(likeId);
        return "Like with id " +likeId+ " has been deleted success.";
    }

    @Override
    public Long getCountLikeByUserId(Long userId) {
        return likeRepository.countByUserId(userId);
    }
}
