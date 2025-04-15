package io.github.sseregit.springchatplatform.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.sseregit.springchatplatform.domain.repository.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findTop10BySenderOrReceiverOrderByTIdDesc(String sender, String receiver);

    /*@Query("""
        SELECT c
        FROM Chat AS c
        WHERE c.sender = :sender or c.receiver = :receiver
        ORDER BY c.tId desc
        """)
    List<Chat> findTop10Chats(@Param("sender") String sender, @Param("reciver") String receiver);*/
}
