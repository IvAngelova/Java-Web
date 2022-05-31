package com.example.books.web;


import com.example.books.model.dto.BookDTO;
import com.example.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> allBooks() {
        List<BookDTO> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
        Optional<BookDTO> bookDTO = bookService.getBookById(id);

        if (bookDTO.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            return ResponseEntity
                    .ok(bookDTO.get());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable("id") Long id){

        //the method could also return boolean
        bookService.deleteBook(id);

        return ResponseEntity
                .noContent()
                .build();
    }


    @PostMapping()
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO, UriComponentsBuilder builder){
       Long bookId = bookService.createBook(bookDTO);

        URI location = builder.path("/books/{id}")
                .buildAndExpand(bookId).toUri();

        return ResponseEntity
                .created(location)
                .build();

    }




}
