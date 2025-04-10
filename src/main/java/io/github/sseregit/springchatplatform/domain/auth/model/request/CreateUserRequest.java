package io.github.sseregit.springchatplatform.domain.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "User를 생성한다.")
public record CreateUserRequest(
    @Schema(description = "유저 이름")
    @NotBlank
    @NotNull
    String name,
    @Schema(description = "유저 비밀번호")
    @NotBlank
    @NotNull
    String password
) {

}
