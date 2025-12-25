package com.pathbros.jwt;


import com.pathbros.enums.Role;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret="superSecretKey";
    private final int jwtExpirationMs=86400000;


    public String generateToken(String eMail, Role role){
        return Jwts.builder()
                .setSubject(eMail)
                .claim("role",role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getUserMailForToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }
        catch (JwtException e){
            return false;
        }
    }
}
