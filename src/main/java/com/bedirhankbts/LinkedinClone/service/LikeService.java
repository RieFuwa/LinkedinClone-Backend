package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.model.Like;
import com.bedirhankbts.LinkedinClone.request.likeRequest.LikeCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LikeService {
    List<LikeDto> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId);

    Like getLikeById(Long likeId);

    ResponseEntity<LikeDto> createLike(LikeCreateRequest newLike);

    String deleteLikeById(Long likeId);
}
