package nintendo.shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nintendo.shop.exception.TokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JWTService {
    @Value("${jwt.expiration.hour.delay}")
    public long EXPIRATION_DELAY = 0;
    @Value("${jwt.secret.key}")
    public String JWT_SECRET_KEY = "";
    public String generateToken(Authentication authentication) {
        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(EXPIRATION_DELAY)))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
    }
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) throws TokenException {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET_KEY).parse(token);
            return true;
        } catch (Exception e) {
            throw new TokenException("Token expired or invalidate.");
        }
    }
}
