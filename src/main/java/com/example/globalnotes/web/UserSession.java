package com.example.globalnotes.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserSession {

    @NonNull
    private UUID id;

    @NonNull
    private String login;

    public void clear(){
        id = null;
        login = null;
    }

    public boolean isLoggedIn(){
        return id !=null ? true : false;
    }
}
