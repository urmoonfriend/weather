package tech.jusan.weather;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
public class EnvConfigTest {
    @SuppressWarnings("resource")
    public static JdbcDatabaseContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:11.1")
    )
            .withReuse(true)
            .withDatabaseName("tech");


    public static final GenericContainer<?> redisContainer = new GenericContainer<>(
            DockerImageName.parse("redis:7.0-alpine")
    )
            .withExposedPorts(6379)
            .withReuse(true);


    @BeforeAll
    public static void beforeAll() {
        postgreSQLContainer.start();
        redisContainer.start();
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("app.db.url", postgreSQLContainer::getJdbcUrl);
        registry.add("app.db.username", postgreSQLContainer::getUsername);
        registry.add("app.db.password", postgreSQLContainer::getPassword);
        registry.add("spring.data.redis.host", redisContainer::getHost);
        registry.add("spring.data.redis.port", () -> redisContainer.getMappedPort(6379));
    }
}
