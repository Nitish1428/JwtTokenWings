package com.exercise.jwtTokenWings.config;

import com.exercise.jwtTokenWings.entity.UserInfo;
import com.exercise.jwtTokenWings.service.CustomUserDetailsService;
import com.exercise.jwtTokenWings.service.JwtServiceClass;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtServiceClass jwtServiceClass ;

    @Autowired
    private CustomUserDetailsService userDetailsService ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String username = null ;
        String token = null ;

        if(authHeader !=null && authHeader.contains("Bearer")){
            token = authHeader.substring(7);
            username = jwtServiceClass.extractUserName(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
             UserDetails userDetails = userDetailsService.loadUserByUsername(username);

             if(jwtServiceClass.isTokenValid(token, userDetails)){
                 UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                 token1.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(token1);
             }
        }
        filterChain.doFilter(request, response);
    }


}
