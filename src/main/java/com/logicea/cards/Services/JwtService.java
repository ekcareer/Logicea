package com.logicea.cards.Services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.logicea.cards.Models.User;
import com.logicea.cards.Repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final String SECRETE_KEY = "0fef981b4b16d615b188f3a5de2452f468f9cc211c7c4ddf261892a909b1e4fb";

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        // Set<String> Userroles = new HashSet<>();

        User userOp = userRepository.findByEmail(user.getUsername()).orElseThrow();
        // for (Role role : userOp.getRole()) {
        // Userroles.add(role.getName());
        // }
        claims.put("Roles", userOp.getRole());

        String token = Jwts
                .builder()
                // .claims(extractClaims)
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSignInKey())
                .compact();
        return token;
    }

    public boolean isTokenValid(String token, UserDetails user) {
        final String username = extractUserName(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETE_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // public String generateToken(UserDetails userDetails) {
    // return generateToken(new HashMap<>(), userDetails);
    // }
}
