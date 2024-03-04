package com.jp.findhospital.domain.comment.repository;

import com.jp.findhospital.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {

    List<Comment> findAllByHospitalId(Long hospitalId);
    List<Comment> findTop5ByOrderByDateTimeDesc();
}
