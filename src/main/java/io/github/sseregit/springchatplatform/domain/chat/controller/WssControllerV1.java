package io.github.sseregit.springchatplatform.domain.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import io.github.sseregit.springchatplatform.domain.chat.model.Message;
import io.github.sseregit.springchatplatform.domain.chat.service.ChatServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WssControllerV1 {

    private final ChatServiceV1 chatServiceV1;

    // WebSocket 클라이언트가 보낸 메시지를 서버에서 처리할 엔드포인트로 매핑
    @MessageMapping("/chat/message/{from}")
    // 반환한 값을 클라이언트에게 전송할 대상을 지정
    @SendTo("/sub/chat")
    public Message receivedMessage(
        // @PathVariable과 비슷한 느낌
        @DestinationVariable String from,
        Message message) {
        log.info("Message Received -> From: {}, to: {}, message: {}", from, message.to(), message);
        chatServiceV1.saveChatMessage(message);
        return message;
    }

}
