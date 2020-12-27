package com.informatorio.actividadfinal.repository;

import java.util.List;

// import com.informatorio.actividadfinal.model.Comment;
import com.informatorio.actividadfinal.model.Post;

// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer>{
    List<Post> findByTitleContaining(String word);
    List<Post> findByPublished(Boolean published);
    // List<Comment> findFirstComments(Pageable pageable);
}
