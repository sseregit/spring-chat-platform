package io.github.sseregit.springchatplatform.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.sseregit.springchatplatform.domain.user.model.response.UserSearchResponse;
import io.github.sseregit.springchatplatform.domain.user.service.UserServiceV1;
import io.github.sseregit.springchatplatform.security.JWTProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "User API", description = "V1 User API")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControllerV1 {

    private final UserServiceV1 userServiceV1;
    private final JWTProvider jwtProvider;

    @Operation(
        summary = "User Name List Search",
        description = "User Name을 기반으로 Like 검색 실행"
    )
    @GetMapping("/search/{name}")
    public UserSearchResponse searchUser(@PathVariable String name,
        @RequestHeader("Authorization") String auth) {
        String token = jwtProvider.extractToken(auth);

        String user = jwtProvider.getUserFromToken(token);

        return userServiceV1.searchUser(name, user);
    }

}
