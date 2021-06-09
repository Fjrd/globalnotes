package com.example.globalnotes.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @Column(updatable = false, nullable = false)
    UUID id;

    @NonNull
    @Column(nullable = false, unique = true, length = 50)
    String login;

    @NonNull
    String password;

    @NonNull
    String name;

    @NonNull
    String email;

    @OneToMany(mappedBy = "user")
    List<Note> notes;
}
