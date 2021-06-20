package com.example.globalnotes.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Setter
@ToString
public class Note {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @Column(updatable = false, nullable = false)
    UUID id;

    @NonNull
    String name;

    @NonNull
    String message;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "users_id")
    User author;


}
