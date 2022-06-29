package com.example.books.service;

import com.example.books.model.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById(Long id);

    void deleteBook(Long id);

    Long createBook(BookDTO bookDTO);

    Long updateBook(BookDTO bookDTO);

    Page<BookDTO> getBooks(int pageNo, int pageSize, String sortBy);
}
