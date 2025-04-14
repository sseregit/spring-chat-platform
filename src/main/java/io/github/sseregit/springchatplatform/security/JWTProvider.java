package io.github.sseregit.springchatplatform.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
}
