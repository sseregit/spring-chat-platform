package io.github.sseregit.springchatplatform.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.sseregit.springchatplatform.domain.repository.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    boolean existsByName(String name);

    @Query("""
        SELECT u.name
        FROM User AS u
        WHERE LOCATE(LOWER(:pattern), LOWER(u.name)) > 0 AND u.name != :user
        """)
    List<String> findNameByNameMatch(String pattern, String user);
}
