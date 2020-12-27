package com.informatorio.actividadfinal.controller;

import java.util.List;

import javax.validation.Valid;

import com.informatorio.actividadfinal.model.Comment;
import com.informatorio.actividadfinal.model.Post;
import com.informatorio.actividadfinal.model.User;
import com.informatorio.actividadfinal.repository.CommentRepository;
import com.informatorio.actividadfinal.repository.PostRepository;
// import com.informatorio.actividadfinal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class CommentController {
    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    // Devuelve todos los comentarios de todos los posts
    @GetMapping("/comment")
    public ResponseEntity<List<Comment>> getAllComment(){
        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
    }

    // Devolver comentario por id
    @GetMapping("/comment/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id){
        return new ResponseEntity<>(commentRepository.findById(id).get(), HttpStatus.OK);
    }
    
    // Devolver todos los comentarios de un post
    @GetMapping("/post/{postId}/comment")
    public ResponseEntity<List<Comment>> getAllCommentByPost(@PathVariable Integer postId){
        Post post = postRepository.findById(postId).get();
        return new ResponseEntity<>(post.getComments(), HttpStatus.OK);
    }

    // Devolver todos los comentarios de un post
    @GetMapping("/post/{postId}/comment/{limit}")
    public ResponseEntity<List<Comment>> getAllCommentByPostLimit(@PathVariable Integer postId, @PathVariable Integer limit){
        Post post = postRepository.findById(postId).get();
        return new ResponseEntity<>(post.getComments().subList(0, limit), HttpStatus.OK);
    }

    // Crear un comentario
    @PostMapping(value="post/{postId}/comment")
    public ResponseEntity<?> createComment(@PathVariable("postId") Integer postId, @RequestBody Comment comment){
        Post post = postRepository.findById(postId).get();
        User user = post.getUser();
        comment.setUser(user);
        comment.setPost(post);
        return new ResponseEntity<>(commentRepository.save(comment),HttpStatus.CREATED);
    }
    
    // Borrar un comentario
    @DeleteMapping("post/{postId}/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        Comment commentDelete = commentRepository.findById(commentId).get();
        commentRepository.delete(commentDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Modificar un Comentario
    @PutMapping("post/{postId}/comment/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable Long commentId, @Valid @RequestBody Comment comment) {
        Comment comment2 = commentRepository.findById(commentId).get();
        comment2.setBody(comment.getBody());
        return new ResponseEntity<>(commentRepository.save(comment2), HttpStatus.OK);
    }
}
