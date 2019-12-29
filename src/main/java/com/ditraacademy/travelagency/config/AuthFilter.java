package com.ditraacademy.travelagency.config;

import com.ditraacademy.travelagency.core.user.UserServices;
import com.ditraacademy.travelagency.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtils jwtUtils ;
    @Autowired
    UserServices userServices ;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        String username = jwtUtils.getUserName(token);
        //String username = jwtUtils.getUserName(token);
        if ( username!=null){
            UserDetails userDetails = userServices.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken  authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null , userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}
