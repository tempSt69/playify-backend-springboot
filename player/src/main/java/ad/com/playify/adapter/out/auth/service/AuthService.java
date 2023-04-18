package ad.com.playify.adapter.out.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ad.com.playify.domain.port.out.AuthPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthService implements AuthPort {

    private BCryptPasswordEncoder passwordEncoder;

    public AuthService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Override
    public String generateToken(String id) {
        return Jwts.builder().setId(id).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    @Override
    public Boolean validateToken(String token, String id) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return id.equals(claims.getId()) && claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Boolean verifyPassword(String input, String persistence) {
        return passwordEncoder.matches(input, persistence);
    }
}
