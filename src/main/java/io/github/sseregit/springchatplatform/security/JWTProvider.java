package io.github.sseregit.springchatplatform.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConfigurationProperties(prefix = "token")
@RequiredArgsConstructor
public class JWTProvider {

    private final String secretKey;
    private final String refreshKey;
    private final long tokenTimeForMinutes;
    private final long refreshTimeForMinutes;

    public String createToken(String name) {
        return JWT.create()
            .withSubject(name)
            .withIssuedAt(Instant.now())
            .withExpiresAt(Instant.now().plus(tokenTimeForMinutes, ChronoUnit.SECONDS))
            .sign(Algorithm.HMAC256(secretKey));
    }

    public String createRefreshToken(String name) {
        return JWT.create()
            .withSubject(name)
            .withIssuedAt(Instant.now())
            .withExpiresAt(Instant.now().plus(refreshTimeForMinutes, ChronoUnit.SECONDS))
            .sign(Algorithm.HMAC256(refreshKey));
    }

}
