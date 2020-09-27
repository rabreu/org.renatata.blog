package org.renatata.blog.controller;

import org.renatata.blog.entity.Comment;
import org.renatata.blog.model.CommentResponse;
import org.renatata.blog.service.CommentService;
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
@RequestMapping("/admin/comments")
public class AdminCommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping(path = "/all/{id}")
    public ResponseEntity<List<CommentResponse>> findCommentsByPostId(@PathVariable(value = "id") Long id) {
        if(commentService.findAllCommentsByPostId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<CommentResponse> commentResponses = new ArrayList<CommentResponse>();

        for(Comment comment : commentService.findAllCommentsByPostId(id)) {
            CommentResponse commentResponse = new CommentResponse(comment.getBody(),
                    comment.getPost().getTitle(),
                    comment.getPostedAt()
            );
            commentResponses.add(commentResponse);
        }

        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }
}
