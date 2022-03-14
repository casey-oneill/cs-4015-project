package com.cs4015.bookstore.bookservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BookServiceApplicationTests {

    @Autowired
    private WebTestClient client;

    void getBookById() {

        int bookId = 1;


//        client.get()
//                .uri("/books/" + bookId)
//                .accept(APPLICATION_JSON_UTF8)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(APPLICATION_JSON_UTF8)
//                .expectBody()
//                .jsonPath("$.bookId").isEqualTo(bookId);
    }

}
