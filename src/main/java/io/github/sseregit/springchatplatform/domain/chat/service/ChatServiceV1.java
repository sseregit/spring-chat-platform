package io.github.sseregit.springchatplatform.domain.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.sseregit.springchatplatform.domain.chat.model.Message;
import io.github.sseregit.springchatplatform.domain.repository.ChatRepository;
import io.github.sseregit.springchatplatform.domain.repository.entity.Chat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatServiceV1 {

    private final ChatRepository chatRepository;

    @Transactional(transactionManager = "createChatTransactionManager")
    public void saveChatMessage(Message message) {
        Chat chat = Chat.builder()
            .sender(message.from())
            .receiver(message.to())
            .message(message.message())
            .build();
        chatRepository.save(chat);
    }
}
