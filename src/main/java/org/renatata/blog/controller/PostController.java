package org.renatata.blog.controller;

import org.renatata.blog.entity.Post;
import org.renatata.blog.model.PostResponse;
import org.renatata.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping()
    public ResponseEntity<List<PostResponse>> findAllActive() {
        if (postService.findAllActive().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<PostResponse> postResponse = new ArrayList<PostResponse>();
        for (Post post : postService.findAllActive())
            postResponse.add(new PostResponse(
                    post.getTitle(),
                    post.getBody(),
                    post.getUser().getRealName(),
                    post.getPostedAt(),
                    post.getStatus()
            ));

        return new ResponseEntity(postResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostResponse> findActiveById(@PathVariable(value = "id") Long id) {
        if (!postService.findActiveById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        return new ResponseEntity<PostResponse>(new PostResponse(
                postService.findActiveById(id).get().getTitle(),
                postService.findActiveById(id).get().getBody(),
                postService.findActiveById(id).get().getUser().getRealName(),
                postService.findActiveById(id).get().getPostedAt(),
                postService.findActiveById(id).get().getStatus()
        ),
                HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<PostResponse>> findAll() {
        if (postService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<PostResponse> postResponse = new ArrayList<PostResponse>();
        for (Post post : postService.findAll())
            postResponse.add(new PostResponse(
                    post.getTitle(),
                    post.getBody(),
                    post.getUser().getRealName(),
                    post.getPostedAt(),
                    post.getStatus()
            ));

        return new ResponseEntity(postResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/all/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable(value = "id") Long id) {
        if (!postService.findById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        return new ResponseEntity<PostResponse>(new PostResponse(
                postService.findById(id).get().getTitle(),
                postService.findById(id).get().getBody(),
                postService.findById(id).get().getUser().getRealName(),
                postService.findById(id).get().getPostedAt(),
                postService.findById(id).get().getStatus()
        ),
                HttpStatus.OK);
    }
}
