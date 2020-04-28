package org.renatata.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Users")
@Getter @Setter
public class User {
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

    @Column
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
