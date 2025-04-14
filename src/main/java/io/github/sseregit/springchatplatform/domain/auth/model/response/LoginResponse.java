package io.github.sseregit.springchatplatform.domain.auth.model.response;

import io.github.sseregit.springchatplatform.common.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Login 응답")
public record LoginResponse(
    @Schema(description = "error code")
    ErrorCode description,
    @Schema(description = "jwt token")
    String token
) {
}
