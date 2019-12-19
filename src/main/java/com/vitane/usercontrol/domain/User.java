package com.vitane.usercontrol.domain;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column()
    private UUID id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "pswd")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}
