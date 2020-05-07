package org.renatata.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String body;

    @CreatedDate
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date postedAt;

    @OneToOne
    private Post post;
}
