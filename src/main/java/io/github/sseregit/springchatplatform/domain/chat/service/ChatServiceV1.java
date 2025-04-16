package io.github.sseregit.springchatplatform.domain.chat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.sseregit.springchatplatform.domain.chat.model.Message;
import io.github.sseregit.springchatplatform.domain.chat.model.response.ChatListResponse;
import io.github.sseregit.springchatplatform.domain.repository.ChatRepository;
import io.github.sseregit.springchatplatform.domain.repository.entity.Chat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatServiceV1 {

    private final ChatRepository chatRepository;

    public ChatListResponse chatList(String from, String to) {
        List<Chat> chats = chatRepository.findTop10BySenderOrReceiverOrderByTIDDesc(
            from, to);

        List<Message> res = chats.stream()
            .map(chat -> new Message(chat.getReceiver(), chat.getSender(), chat.getMessage()))
            .toList();

        return new ChatListResponse(res);
    }

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
