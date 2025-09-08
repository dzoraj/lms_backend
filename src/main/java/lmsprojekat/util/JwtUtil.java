package lmsprojekat.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lmsprojekat.model.users.Role;
import lmsprojekat.model.users.User;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "aet4liUghPwst7qpDDu+IZc6XzrrPlWurnm65/cLxoc=";
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        List<String> roleNames = user.getRoles()
                                     .stream()
                                     .map(Role::getName)
                                     .map(name -> name.startsWith("ROLE_") ? name : "ROLE_" + name)
                                     .collect(Collectors.toList());

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("roles", roleNames)
                .claim("id", user.getId()) 
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key)
                .compact();
    }

    public String generateTokenFromUserDetails(UserDetails userDetails, Long userId) {
        List<String> roles = userDetails.getAuthorities()
                                        .stream()
                                        .map(auth -> {
                                            String name = auth.getAuthority();
                                            return name.startsWith("ROLE_") ? name : "ROLE_" + name;
                                        })
                                        .collect(Collectors.toList());

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", roles)
                .claim("id", userId) 
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Long extractUserId(String token) {
        Object idClaim = Jwts.parser()
                             .verifyWith(key)
                             .build()
                             .parseSignedClaims(token)
                             .getPayload()
                             .get("id");  

        if (idClaim instanceof Number) {
            return ((Number) idClaim).longValue();
        } else if (idClaim instanceof String) {
            try {
                return Long.parseLong((String) idClaim);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }


    public boolean validateToken(String token, String email) {
        String extractedEmail = extractEmail(token);
        return extractedEmail.equals(email) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}
