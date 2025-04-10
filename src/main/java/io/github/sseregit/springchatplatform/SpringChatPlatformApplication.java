package io.github.sseregit.springchatplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringChatPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringChatPlatformApplication.class, args);
    }

}
