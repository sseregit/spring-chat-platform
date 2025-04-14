package io.github.sseregit.springchatplatform.domain.auth.service;

import static io.github.sseregit.springchatplatform.common.exception.ErrorCode.*;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.sseregit.springchatplatform.common.exception.CustomException;
import io.github.sseregit.springchatplatform.domain.auth.model.request.CreateUserRequest;
import io.github.sseregit.springchatplatform.domain.auth.model.request.LoginRequest;
import io.github.sseregit.springchatplatform.domain.auth.model.response.CreateUserResponse;
import io.github.sseregit.springchatplatform.domain.auth.model.response.LoginResponse;
import io.github.sseregit.springchatplatform.domain.repository.UserRepository;
import io.github.sseregit.springchatplatform.domain.repository.entity.User;
import io.github.sseregit.springchatplatform.domain.repository.entity.UserCredentials;
import io.github.sseregit.springchatplatform.security.Hasher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final Hasher hasher;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByName(request.name())
            .orElseThrow(() -> {
                log.error("User {} not found", request.name());
                throw new CustomException(NOT_EXIST_USER);
            });

        if (isPasswordMatch(request.password(), user.getUserCredentials().getHashedPassword())) {
            return new LoginResponse(SUCCESS, "Token");
        }

        throw new CustomException(MISS_MATCH_PASSWORD);
    }

    private boolean isPasswordMatch(String requestPassword, String userHashedPassword) {
        String hashedValue = hasher.getHashingValue(requestPassword);

        return userHashedPassword.equals(hashedValue);
    }

    @Transactional(transactionManager = "createUserTransactionManager")
    public CreateUserResponse createUser(CreateUserRequest request) {
        Optional<User> user = userRepository.findByName(request.name());

        if (user.isPresent()) {
            log.error("User {} already exists", request.name());
            throw new CustomException(USER_ALREADY_EXISTS);
        }

        try {
            User newUser = newUser(request.name());
            UserCredentials userCredentials = newUserCredentials(request.password(), newUser);
            newUser.setUserCredentials(userCredentials);

            User savedUser = userRepository.save(newUser);

            if (savedUser == null) {
                log.error("Failed to create user {}", request.name());
                throw new CustomException(USER_SAVED_FAILED);
            }
        } catch (Exception e) {
            log.error("Failed to create user {}", request.name());
            throw new CustomException(USER_SAVED_FAILED);
        }

        return new CreateUserResponse(request.name());
    }

    private User newUser(String name) {
        return User.builder()
            .name(name)
            .build();
    }

    private UserCredentials newUserCredentials(String password, User user) {

        String hashedValue = hasher.getHashingValue(password);

        return UserCredentials
            .builder()
            .user(user)
            .hashedPassword(hashedValue)
            .build();
    }
}
