package org.renatata.blog.repository;

import org.renatata.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "SELECT * FROM COMMENTS " +
            "WHERE POST_ID = :postId",
            nativeQuery = true)
    List<Comment> findAllCommentsByPostId(@Param("postId") Long postId);
}
