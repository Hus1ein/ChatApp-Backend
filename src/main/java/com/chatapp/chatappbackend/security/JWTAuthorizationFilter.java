package com.chatapp.chatappbackend.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, authorization");;
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH");

        if(request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            String jwt = request.getHeader(SecurityParams.HEADER_KEY_NAME);
            if (jwt == null || !jwt.startsWith(SecurityParams.HEADER_TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            FirebaseToken firebaseToken;
            try {
                firebaseToken = firebaseAuth.verifyIdTokenAsync
                        (jwt.substring(SecurityParams.HEADER_TOKEN_PREFIX.length())).get();

                String username = FirebaseAuth.getInstance().getUser(firebaseToken.getUid()).getPhoneNumber();

                if (firebaseAuth.getUser(firebaseToken.getUid()).isDisabled()) {
                    filterChain.doFilter(request, response);
                    return;
                }

                Collection<GrantedAuthority> authorities = new ArrayList<>();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
                filterChain.doFilter(request, response);

            } catch (InterruptedException | ExecutionException | FirebaseAuthException e) {
                log.error("Error while authorization an user. Exception: {}", e.getMessage());

                filterChain.doFilter(request, response);
            }
        }


    }

}

