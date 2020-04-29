package org.renatata.blog.repository;

import org.renatata.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();

    Optional<Post> findById(Long id);

    @Query(value = "SELECT * FROM POSTS " +
            "WHERE STATUS = 0",
            nativeQuery = true)
    List<Post> findAllActive();

    @Query(value = "SELECT * FROM POSTS " +
            "WHERE ID = :Id " +
            "AND STATUS = 0",
            nativeQuery = true)
    Optional<Post> findActiveById(@Param("Id") Long Id);
}
