package com.chatapp.chatappbackend.security;

import com.chatapp.chatappbackend.firebase.authentication.FirebaseAuthService;
import com.chatapp.chatappbackend.rdb.entities.CompanyEntity;
import com.chatapp.chatappbackend.rest.services.interfaces.CompaniesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final FirebaseAuthService firebaseAuthService;
    private final CompaniesService companiesService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, authorization");;
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH");

        if(request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            String userAgent = request.getHeader(SecurityParams.USER_AGENT_HEADER);
            String jwt = request.getHeader(SecurityParams.AUTHORIZATION_HEADER);
            String username;

            try {

                if (userAgent.equals(SecurityParams.WEB_USER_AGENT)) {
                    String token = jwt.substring(SecurityParams.AUTHORIZATION_HEADER_PREFIX.length());
                    username = SecurityParams.COMPANY_PREFIX + firebaseAuthService.authenticateUser(token);
                } else if (userAgent.equals(SecurityParams.MOBILE_USER_AGENT)){
                    String apiKey = request.getHeader(SecurityParams.API_KEY_HEADER);
                    CompanyEntity company = companiesService.getByApiKey(apiKey);
                    username = "";
                    //TODO find userId by asking client url.
                    //TODO username = SecurityParams.COMPANY_PREFIX + companyId + "," + SecurityParams.USER_PREFIX + userId;
                } else {
                    filterChain.doFilter(request, response);
                    return;
                }

                Collection<GrantedAuthority> authorities = new ArrayList<>();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                filterChain.doFilter(request, response);
            }
        }

    }

}

