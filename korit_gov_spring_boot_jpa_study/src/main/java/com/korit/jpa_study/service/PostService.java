package com.korit.jpa_study.service;

import com.korit.jpa_study.dto.AddPostReqDto;
import com.korit.jpa_study.dto.ApiRespDto;
import com.korit.jpa_study.dto.EditPostReqDto;
import com.korit.jpa_study.entity.Post;
import com.korit.jpa_study.repository.PostRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public ApiRespDto<?> addPost(AddPostReqDto addPostReqDto) {
        Optional<Post> foundPost = postRepository.findPostByTitle(addPostReqDto.getTitle());
        if (foundPost.isPresent()) {
            return new ApiRespDto<>("failed", "추가실패", addPostReqDto.getTitle());
        }
        return new ApiRespDto<>("success", "추가성공", postRepository.save(addPostReqDto.toEntity()));
    }

    public ApiRespDto<?> getPostAll() {
        return new ApiRespDto<>("success", "전체 조회성공", postRepository.findAll());
    }

    public ApiRespDto<?> editPost(EditPostReqDto editPostReqDto) {
        Optional<Post> foundPost = postRepository.findById(editPostReqDto.getPostId());
        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지않습니다", null);
        }
        Post post = foundPost.get();
        post.setTitle(editPostReqDto.getTitle());
        post.setContent(editPostReqDto.getContent());
        post.setUpdateDt(LocalDateTime.now());
        Post updatedPost = postRepository.save(post);
        return new ApiRespDto<>("success", "수정성공", updatedPost);
    }

    public ApiRespDto<?> removePost(Integer postId) {
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지않습니다", null);
        }
        postRepository.deleteById(postId);
        return new ApiRespDto<>("success", "삭제 성공", null);
    }

    public ApiRespDto<?> getPostByPostId(Integer postId) {
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않습니다", null);
        }
        return new ApiRespDto<>("success", "단건조회성공", foundPost);
    }

    public ApiRespDto<?> getPostListByUserId(Integer userId){
        return new ApiRespDto<>("success", "조회성공", postRepository.findAllByUserId(userId));
    }
}
