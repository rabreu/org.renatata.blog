package org.renatata.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
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
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @NotNull
    private String body;

    @Column
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Status status;

    @Column
    @CreatedDate
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date postedAt;

    @ManyToOne
    @NotNull
    private User user;
}
