package org.renatata.blog.controller;

import org.json.JSONException;
import org.renatata.blog.entity.Comment;
import org.renatata.blog.model.CommentResponse;
import org.renatata.blog.service.CommentService;
import org.renatata.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<CommentResponse>> findActiveCommentsByPostId(@PathVariable(value = "id") Long id) {
        if(commentService.findActiveCommentsByPostId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<CommentResponse> commentResponses = new ArrayList<>();
        for(Comment comment : commentService.findActiveCommentsByPostId(id))
            commentResponses.add(new CommentResponse(
                    comment.getBody(),
                    comment.getPost().getTitle(),
                    comment.getPostedAt()
            ));

        return new ResponseEntity<List<CommentResponse>>(commentResponses, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<CommentResponse> add(@RequestBody Comment comment, @RequestHeader(value = "post") Long postId) throws JSONException {
        try {
            if(!postService.findById(postId).isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            comment.setPost(postService.findById(postId).get());
            Comment savedComment = commentService.add(comment);

            return new ResponseEntity<>(
                    new CommentResponse(
                            savedComment.getBody(),
                            savedComment.getPost().getTitle(),
                            savedComment.getPostedAt()
                    ),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}