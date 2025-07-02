package nrabello.back.core.service;

import nrabello.back.core.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface IJwtService {

    String generateToken(User user);

    String getEmailFromLoggedUser();

    String extractUsername(String token);

    Date extractExpiration(String token);

    <T> T extractClaim(String token, java.util.function.Function<io.jsonwebtoken.Claims, T> claimsResolver);

    boolean validateToken(String token, UserDetails userDetails);
}
