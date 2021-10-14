package com.chatapp.chatappbackend.firebase;

import com.chatapp.chatappbackend.firebase.exceptions.FirebaseAuthenticationException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FirebaseAuthServiceImpl implements FirebaseAuthService{

    @Override
    public String authenticateUser(String token) throws FirebaseAuthenticationException {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        try {
            FirebaseToken firebaseToken = firebaseAuth.verifyIdTokenAsync(token).get();
            UserRecord user = firebaseAuth.getUser(firebaseToken.getUid());

            if (user.isDisabled()) {
                log.error("The user: {} is disabled!", user.getPhoneNumber());
                throw new FirebaseAuthenticationException("The user: " + user.getPhoneNumber() + " is disabled!");
            }

            log.info("Firebase successfully authenticated the user: {}.", user.getPhoneNumber());
            return user.getPhoneNumber();
        } catch (InterruptedException | ExecutionException | FirebaseAuthException e) {
            log.error("Error was received from firebase while authentication an user. Exception: {}", e.getLocalizedMessage());
            throw new FirebaseAuthenticationException("Error while authentication an user");
        }
    }

}
