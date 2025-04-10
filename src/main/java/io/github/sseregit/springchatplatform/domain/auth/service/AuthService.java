package io.github.sseregit.springchatplatform.domain.auth.service;

import org.springframework.stereotype.Service;

import io.github.sseregit.springchatplatform.domain.auth.model.request.CreateUserRequest;
import io.github.sseregit.springchatplatform.domain.auth.model.response.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    public CreateUserResponse createUser(CreateUserRequest request) {
        return new CreateUserResponse(request.name());
    }
}
