package org.renatata.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private String body;
    private String postedOn;
    private Date postedAt;
}
