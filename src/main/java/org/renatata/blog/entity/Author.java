package org.renatata.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AUTHORS")
@Getter @Setter
public class Author {
    @Id
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column
    private String password;
}
