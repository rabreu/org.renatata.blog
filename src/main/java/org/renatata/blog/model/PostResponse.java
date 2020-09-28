package org.renatata.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.renatata.blog.entity.Status;

import java.util.Date;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String body;
    private String author;
    private Date postedAt;
    private Status status;

}
