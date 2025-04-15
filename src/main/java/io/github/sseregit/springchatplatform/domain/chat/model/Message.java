package io.github.sseregit.springchatplatform.domain.chat.model;

public record Message(
    String to,
    String from,
    String message
) {

}
