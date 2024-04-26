package com.jvnyor.posts.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository.saveAll(List.of(new Post(1, 1, "Hello, World!", "This is my first post", null)));
    }

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void shouldReturnPostByTitle() {
        Post post = postRepository.findByTitle("Hello, World!");
        assertThat(post).isNotNull();
        assertThat(post.title()).isEqualTo("Hello, World!");
    }

    @Test
    void shouldNotReturnPostByTitle() {
        Post post = postRepository.findByTitle("Non existent title");
        assertThat(post).isNull();
    }
}