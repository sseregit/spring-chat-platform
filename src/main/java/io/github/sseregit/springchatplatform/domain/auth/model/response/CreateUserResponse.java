package io.github.sseregit.springchatplatform.domain.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "유저 생성 응답")
public record CreateUserResponse(
    @Schema(description = "성공 유무")
    String code
) {
}
