package com.pathbros.filter;

import com.pathbros.jwt.JwtUtils;
import com.pathbros.service.userdetailservice.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailService userDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader=request.getHeader("Authorization");
        String token=null;
        String userEmail=null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7).trim();
            try {
                if(jwtUtils.validateToken(token)){
                    userEmail=jwtUtils.getUserMailForToken(token);
                }
            }
            catch (Exception e){
                System.out.println("JWT validation failed: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid or Expired Token");
                return;
            }
        }
        if (userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userDetailService.loadUserByUsername(userEmail);
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(
                            userDetails,null,userDetails.getAuthorities()
                    );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);
    }
}
