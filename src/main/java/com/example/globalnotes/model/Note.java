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
public class Note {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @Column(updatable = false, nullable = false)
    UUID id;

    @NonNull
    String name;

    @NonNull
    String body;

    @ManyToOne
    User owner;


}
