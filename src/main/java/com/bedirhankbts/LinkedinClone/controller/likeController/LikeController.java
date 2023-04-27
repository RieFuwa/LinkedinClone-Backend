package com.bedirhankbts.LinkedinClone.controller.likeController;

import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.model.Like;
import com.bedirhankbts.LinkedinClone.request.likeRequest.LikeCreateRequest;
import com.bedirhankbts.LinkedinClone.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping("/getAll")
    public List<LikeDto> getAllLike(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId,postId);

    }

    @GetMapping("/{likeId}")
    public LikeDto getLikeById(@PathVariable("likeId")Long likeId){
        Like like = likeService.getLikeById(likeId);
        return new LikeDto(like);
    }
    @PostMapping("/add")
    public ResponseEntity<LikeDto> createLike(@RequestBody LikeCreateRequest newLike) {
        return likeService.createLike(newLike);
    }
    @DeleteMapping("/{likeId}")
    public String deleteLikeById(@PathVariable("likeId") Long likeId){
        return likeService.deleteLikeById(likeId);
    }

}
