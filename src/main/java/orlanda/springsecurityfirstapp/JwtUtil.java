package orlanda.springsecurityfirstapp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.ZonedDateTime;
import java.util.Date;

public class JwtUtil {

    private static final String secret = "sas";

    public String generateToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("user details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("robert")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String verifyAndDecodeToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("robert")
                .build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        return decodedJWT.getToken();
    }

}
