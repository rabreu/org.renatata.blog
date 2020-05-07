package org.renatata.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Posts")
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Status status;

    @Column
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private Date postedAt;

    @ManyToOne
    @NotNull
    private User user;
}
