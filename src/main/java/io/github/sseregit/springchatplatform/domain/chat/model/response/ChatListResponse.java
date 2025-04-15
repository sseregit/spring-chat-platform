package io.github.sseregit.springchatplatform.domain.chat.model.response;

import java.util.List;

import io.github.sseregit.springchatplatform.domain.chat.model.Message;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Chatting List")
public record ChatListResponse(
    @Schema(description = "return Message : []")
    List<Message> result
) {
}
