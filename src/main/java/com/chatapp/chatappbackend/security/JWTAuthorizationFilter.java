package com.chatapp.chatappbackend.security;

import com.chatapp.chatappbackend.firebase.FirebaseAuthService;
import com.chatapp.chatappbackend.firebase.exceptions.FirebaseAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Value("${socketServer.apiKey}")
    private String socketServerApiKey;

    @Value("${socketServer.name}")
    private String socketServerName;

    private final FirebaseAuthService firebaseAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, authorization");;
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH");

        if(request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            String apiKey = request.getHeader(SecurityParams.API_KEY_HEADER);
            String jwt = request.getHeader(SecurityParams.AUTHORIZATION_HEADER);
            String username;

            try {

                if (apiKey != null && apiKey.startsWith(SecurityParams.API_KEY_HEADER_PREFIX) &&
                        apiKey.substring(SecurityParams.API_KEY_HEADER_PREFIX.length()).equals(socketServerApiKey)) {

                    log.info("Socket-server request was detected.");
                    if (jwt != null && jwt.startsWith(SecurityParams.AUTHORIZATION_HEADER_PREFIX)) {
                        username = jwt.substring(SecurityParams.AUTHORIZATION_HEADER_PREFIX.length());
                    } else {
                        username = socketServerName;
                    }

                } else {
                    if (jwt == null || !jwt.startsWith(SecurityParams.AUTHORIZATION_HEADER_PREFIX)) {
                        log.error("The request was received without a token!");
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        username = firebaseAuthService.authenticateUser(jwt.substring(SecurityParams.AUTHORIZATION_HEADER_PREFIX.length()));
                    }
                }

                Collection<GrantedAuthority> authorities = new ArrayList<>();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
                filterChain.doFilter(request, response);
            } catch (FirebaseAuthenticationException e) {
                filterChain.doFilter(request, response);
            }
        }

    }

}

