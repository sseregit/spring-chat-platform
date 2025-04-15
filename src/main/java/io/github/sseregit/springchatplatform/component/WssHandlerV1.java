package io.github.sseregit.springchatplatform.component;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.sseregit.springchatplatform.domain.chat.model.Message;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WssHandlerV1 extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            String payload = message.getPayload();
            Message payloadMessage = objectMapper.readValue(payload, Message.class);

            session.sendMessage(new TextMessage(payload));
        } catch (Exception e) {

        }
    }
}
