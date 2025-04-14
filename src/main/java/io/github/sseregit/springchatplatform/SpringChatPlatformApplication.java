package io.github.sseregit.springchatplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
public class SpringChatPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringChatPlatformApplication.class, args);
    }

}
