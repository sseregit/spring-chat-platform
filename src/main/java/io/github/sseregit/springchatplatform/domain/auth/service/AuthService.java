package io.github.sseregit.springchatplatform.domain.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.sseregit.springchatplatform.domain.auth.model.request.CreateUserRequest;
import io.github.sseregit.springchatplatform.domain.auth.model.response.CreateUserResponse;
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

    @Transactional(transactionManager = "createUserTransactionManager")
    public CreateUserResponse createUser(CreateUserRequest request) {
        Optional<User> user = userRepository.findByName(request.name());

        if (user.isPresent()) {

        }

        try {
            User newUser = newUser(request.name());
            UserCredentials userCredentials = newUserCredentials(request.password(), newUser);
            newUser.setUserCredentials(userCredentials);

            User savedUser = userRepository.save(newUser);

            if (savedUser == null) {

            }
        } catch (Exception e) {

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
