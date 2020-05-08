package org.renatata.blog.service;

import org.renatata.blog.entity.Post;
import org.renatata.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAllActive() {
        return postRepository.findAllActive();
    }

    public Optional<Post> findActiveById(Long id) {
        return postRepository.findActiveById(id);
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }

    public Post update(Post post, Long id) {
        if (!this.findById(id).isPresent())
            return null;

        return postRepository.save(post);
    }
}
