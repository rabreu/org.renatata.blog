package org.renatata.blog.controller;

import org.json.JSONException;
import org.renatata.blog.entity.Post;
import org.renatata.blog.model.PostResponse;
import org.renatata.blog.service.PostService;
import org.renatata.blog.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private SecurityService securityService;

    @GetMapping()
    public ResponseEntity<List<PostResponse>> findAllActive() {
        if (postService.findAllActive().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<PostResponse> postResponse = new ArrayList<PostResponse>();
        for (Post post : postService.findAllActive())
            postResponse.add(new PostResponse(
                    post.getId(),
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
                postService.findActiveById(id).get().getId(),
                postService.findActiveById(id).get().getTitle(),
                postService.findActiveById(id).get().getBody(),
                postService.findActiveById(id).get().getUser().getRealName(),
                postService.findActiveById(id).get().getPostedAt(),
                postService.findActiveById(id).get().getStatus()
        ),
                HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<PostResponse> add(@RequestBody Post post) throws JSONException {
        try {
            post.setUser(securityService.getUserAuthenticated());
            Post savedPost = postService.add(post);

            return new ResponseEntity<>(
                    new PostResponse(
                            savedPost.getId(),
                            savedPost.getTitle(),
                            savedPost.getBody(),
                            savedPost.getUser().getRealName(),
                            savedPost.getPostedAt(),
                            savedPost.getStatus()
                    ),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity<PostResponse> update(@RequestBody Post post, @PathVariable(value = "id") Long id) throws JSONException {
        try {
            Optional<Post> postExists = postService.findById(id);

            if (!postExists.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            if (securityService.getUserAuthenticated() != postExists.get().getUser())
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

            if (post.getTitle() != null)
                postExists.get().setTitle(post.getTitle());

            if (post.getBody() != null)
                postExists.get().setBody(post.getBody());

            if (post.getStatus() != null)
                postExists.get().setStatus(post.getStatus());

            postService.update(postExists.get(), id);

            return new ResponseEntity<>(
                    new PostResponse(
                            postExists.get().getId(),
                            postExists.get().getTitle(),
                            postExists.get().getBody(),
                            postExists.get().getUser().getRealName(),
                            postExists.get().getPostedAt(),
                            postExists.get().getStatus()
                    ),
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
