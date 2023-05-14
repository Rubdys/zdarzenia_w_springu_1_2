package com.example.zdarzenia_w_springu_1_2.service;

import com.example.zdarzenia_w_springu_1_2.helper.BookCreator;
import com.example.zdarzenia_w_springu_1_2.model.BookCreationParamsDTO;
import com.example.zdarzenia_w_springu_1_2.model.BookDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class BookService {
    private final BookCreator bookCreator;

    public BookService(BookCreator bookCreator) {
        this.bookCreator = bookCreator;
    }

    public Flux<BookDTO> generateBooks(BookCreationParamsDTO bookCreationParams) {
        Flux<BookDTO> bookFlux = Flux.empty();
        int numberOfBooks = bookCreationParams.getNumberOfBooks();
        for(int i = 0; i < numberOfBooks; i++){
            bookFlux = bookFlux.concatWith(Flux.just(bookCreator.generateBook()));
        }
        return bookFlux;
    }
}
