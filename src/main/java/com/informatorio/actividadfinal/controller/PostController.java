package com.informatorio.actividadfinal.controller;

import java.util.List;

import javax.validation.Valid;

import com.informatorio.actividadfinal.model.Post;
import com.informatorio.actividadfinal.model.User;
import com.informatorio.actividadfinal.repository.PostRepository;
import com.informatorio.actividadfinal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    // Crear un post
    @PostMapping("/{user_id}")
    public ResponseEntity<?> create(@PathVariable Integer user_id, @RequestBody Post post){
        User user = userRepository.findById(user_id).get();
        post.setAuthor(user);
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    // Eliminar un post
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        postRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Modificar un post
    @PutMapping("/{postId}")
    public ResponseEntity<?> edit(@PathVariable Integer postId, @Valid @RequestBody Post post) {
        Post p = postRepository.findById(postId).get();
        p.setTitle(post.getTitle());
        p.setBody(post.getBody());
        p.setPublished(post.getPublished());
        p.setDescription(post.getDescription());
        p.setCreationDate(post.getCreationDate());
        return new ResponseEntity<>(postRepository.save(p), HttpStatus.OK);
    }

    // Listar todos los posts
    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAll(){
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    // Devolver post mediante id
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Integer postId) {
        return new ResponseEntity<>(postRepository.findById(postId).get(),HttpStatus.OK);
    }

    //Devolver post filtrados por una palabra en el título
    @GetMapping("filter/title/{word}")
    public ResponseEntity<List<Post>> getPostByTitleContaining(@PathVariable String word){
        return new ResponseEntity<>(postRepository.findByTitleContaining(word),HttpStatus.OK);
    }

    // Devolver post que no fueron publicados
    @GetMapping(value="notPublished")
    public ResponseEntity<?> getNotPublished() {
        return new ResponseEntity<>(postRepository.findByPublished(false), HttpStatus.OK);
    }
    
}
