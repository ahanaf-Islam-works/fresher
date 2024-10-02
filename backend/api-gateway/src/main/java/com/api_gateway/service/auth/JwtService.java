package com.api_gateway.service.auth;

import com.api_gateway.dto.UserContext;
import com.api_gateway.exception.ApiException;
import com.api_gateway.model.user.User;
import com.api_gateway.model.user.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpirationTime;

    // Extract username (subject) from token
    public UserContext extractUserContext(String token)  {
        Claims claims = extractAllClaims(token);
        UserContext userContext = new UserContext();
        userContext.setUserName(claims.getSubject());
        userContext.setRollNumber(claims.get("rollNumber", Integer.class));
        userContext.setUserType(UserType.valueOf(claims.get("userType", String.class)));
        userContext.setEmail(claims.get("email", String.class));
        userContext.setExpiresIn(claims.getExpiration().getTime());
        return userContext;
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rollNumber", user.getRollNumber());
        claims.put("userType", user.getUserType().toString());
        claims.put("email", user.getEmail());
        claims.put("expires", System.currentTimeMillis() + jwtExpirationTime);
        return buildToken(claims, user, jwtExpirationTime);
    }


    // Build the token with claims, username, issue and expiration dates
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // username as subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    // Extract all claims from token
    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new ApiException("Faulty jwt token", HttpStatus.BAD_REQUEST);
        }
    }

    // Get the signing key for the token (using secret key)
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(Long currentTokenExpiration){
        return true;
    }
}