package com.ditraacademy.travelagency.utils;


    import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

    @Component
    public class JWTUtils {

        private final String JWT_SECRET = "DITRASECRET";

        public String generateToken(String username) {

            String token = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                    .compact();

            return token;
        }

        public String getUserName ( String token ){
            try{
                return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
            }catch (Exception e){
                System.out.println(e.getMessage());
                return  null ;
            }
        }


    }

