package com.korit.jpa_study.controller;

import com.korit.jpa_study.dto.AddPostReqDto;
import com.korit.jpa_study.dto.EditPostReqDto;
import com.korit.jpa_study.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* JPA (Java Persistence API)
* 객체 지향 언어의 객체와 관계형 데이터베이스의 데이블간의 매핑을 자동으로 처리
* > SQL을 직접 쓰지 않고 자바 객체 중심으로 DB를 다룬다
* 장점 : SQL없이 빠르게 CRUD가능, 코드량 줄여줌
* 단점 : 복잡한 쿼리는 어렵고, 디버깅이 힘들다.
* */

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody AddPostReqDto addPostReqDto) {
        return ResponseEntity.ok(postService.addPost(addPostReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPostAll() {
        return ResponseEntity.ok(postService.getPostAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostByPostId(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.getPostByPostId(postId));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editPost(@RequestBody EditPostReqDto editPostReqDto) {
        return ResponseEntity.ok(postService.editPost(editPostReqDto));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removePost(@RequestParam Integer postId) {
        return ResponseEntity.ok(postService.removePost(postId));
    }

    @GetMapping("/get/user")
    public ResponseEntity<?> getPostListByUserId (@RequestParam Integer userId) {
        return ResponseEntity.ok(postService.getPostListByUserId(userId));
    }
}
