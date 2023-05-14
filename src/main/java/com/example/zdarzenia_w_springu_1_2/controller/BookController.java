package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.model.BookCreationParamsDTO;
import com.example.zdarzenia_w_springu_1_2.model.BookDTO;
import com.example.zdarzenia_w_springu_1_2.service.BookService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "createBooks")
    public Flux<BookDTO> createBooks(@RequestBody BookCreationParamsDTO bookCreationParams){
        return bookService.generateBooks(bookCreationParams)
                .delayElements(Duration.ofSeconds(2))
                .log();
    }
}
