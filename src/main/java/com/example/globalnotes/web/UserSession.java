package com.example.globalnotes.web;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class UserSession {

    @NonNull
    private UUID id;

    @NonNull
    private String login;

    public void clear(){
        id = null;
        login = null;
    }
}
