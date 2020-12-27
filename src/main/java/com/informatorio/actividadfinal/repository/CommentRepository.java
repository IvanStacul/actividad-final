package com.informatorio.actividadfinal.repository;

import java.util.List;

import com.informatorio.actividadfinal.model.Comment;
import com.informatorio.actividadfinal.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByPost(Post post);
    
}