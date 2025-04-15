package io.github.sseregit.springchatplatform.security;

import static io.github.sseregit.springchatplatform.common.exception.ErrorCode.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.github.sseregit.springchatplatform.common.exception.CustomException;
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

    public DecodedJWT checkAccessForRefresh(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
            log.error("token must be expired: {}", decodedJWT.getSubject());
            throw new CustomException(ACCESS_TOKEN_IS_NOT_EXPIRED);
        } catch (AlgorithmMismatchException | SignatureVerificationException | InvalidClaimException e) {
            throw new CustomException(TOKEN_IS_INVALID);
        } catch (TokenExpiredException e) {
            return JWT.decode(token);
        }
    }

    public DecodedJWT decodedAccessToken(String token) {
        return decodedTokenAfterVerify(token, secretKey);
    }

    public DecodedJWT decodedRefreshToken(String token) {
        return decodedTokenAfterVerify(token, refreshKey);
    }

    private DecodedJWT decodedTokenAfterVerify(String token, String key) {
        try {
            return JWT.require(Algorithm.HMAC256(key)).build().verify(token);
        } catch (AlgorithmMismatchException | SignatureVerificationException | InvalidClaimException e) {
            throw new CustomException(TOKEN_IS_INVALID);
        } catch (TokenExpiredException e) {
            throw new CustomException(TOKEN_IS_EXPIRED);
        }
    }

    public DecodedJWT decodedJWT(String token) {
        return JWT.decode(token);
    }
}
