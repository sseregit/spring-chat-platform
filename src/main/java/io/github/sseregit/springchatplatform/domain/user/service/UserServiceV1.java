package io.github.sseregit.springchatplatform.domain.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.sseregit.springchatplatform.common.exception.ErrorCode;
import io.github.sseregit.springchatplatform.domain.repository.UserRepository;
import io.github.sseregit.springchatplatform.domain.user.model.response.UserSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceV1 {

    private final UserRepository userRepository;

    public UserSearchResponse searchUser(String name, String user) {
        List<String> names = userRepository.findNameByNameMatch(name, user);

        return new UserSearchResponse(ErrorCode.SUCCESS, names);
    }
}
