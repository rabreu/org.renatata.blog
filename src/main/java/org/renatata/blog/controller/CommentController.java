package org.renatata.blog.controller;

import org.renatata.blog.entity.Comment;
import org.renatata.blog.model.CommentResponse;
import org.renatata.blog.service.CommentService;
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
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

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

    @GetMapping(path = "/all/{id}")
    public ResponseEntity<List<Comment>> findCommentsByPostId(@PathVariable(value = "id") Long id) {
        if(commentService.findAllCommentsByPostId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(commentService.findAllCommentsByPostId(id), HttpStatus.OK);
    }
}