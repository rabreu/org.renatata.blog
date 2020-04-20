package org.renatata.blog.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "POSTS")
@Getter @Setter
public class Post {
    @Id
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    @CreatedDate
    private Date postedAt;

    @ManyToOne
    @NotNull
    private Author author;
}
