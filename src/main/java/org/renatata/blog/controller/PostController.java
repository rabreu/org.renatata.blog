package org.renatata.blog.controller;

import org.renatata.blog.entity.Comment;
import org.renatata.blog.entity.Post;
import org.renatata.blog.service.CommentService;
import org.renatata.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping()
    public ResponseEntity<List<Post>> findAllActive() {
        return new ResponseEntity(postService.findAllActive(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Post>> findActiveById(@PathVariable(value = "id") Long id) {
        if (!postService.findActiveById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(postService.findActiveById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Post>> findAll() {
        return new ResponseEntity(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/all/{id}")
    public ResponseEntity<Optional<Post>> findById(@PathVariable(value = "id") Long id) {
        if (!postService.findById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }
}
