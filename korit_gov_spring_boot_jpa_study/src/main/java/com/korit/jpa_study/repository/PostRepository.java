package com.korit.jpa_study.repository;

import com.korit.jpa_study.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findPostByTitle(String title);

    List<Post> findAllByUserId(Integer userId);
}
