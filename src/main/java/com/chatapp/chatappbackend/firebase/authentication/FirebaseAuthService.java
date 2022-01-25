package com.chatapp.chatappbackend.firebase.authentication;

import com.chatapp.chatappbackend.firebase.exceptions.FirebaseAuthenticationException;

public interface FirebaseAuthService {

    String authenticateUser(String token) throws FirebaseAuthenticationException;

}
