package org.renatata.blog.service;

import org.renatata.blog.entity.Comment;
import org.renatata.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAllCommentsByPostId(Long postId) {
        return commentRepository.findAllCommentsByPostId(postId);
    }
}
