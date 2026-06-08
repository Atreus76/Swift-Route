package com.example.swiftroute;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest
@Testcontainers
public class BaseIntegrationTest {

    private static final Dotenv dotenv = Dotenv.load();

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName(dotenv.get("SPRING_DATABASE_NAME"))
            .withUsername(dotenv.get("SPRING_DATASOURCE_USERNAME"))
            .withPassword(dotenv.get("SPRING_DATASOURCE_PASSWORD"));

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}
