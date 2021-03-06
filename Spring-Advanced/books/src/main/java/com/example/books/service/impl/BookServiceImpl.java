package com.example.books.service.impl;

import com.example.books.model.dto.AuthorDTO;
import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.AuthorEntity;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository
                .findById(id)
                .map(this::map);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Long createBook(BookDTO bookDTO) {
        BookEntity newBook = modelMapper.map(bookDTO, BookEntity.class);
        Optional<AuthorEntity> authorByName = authorRepository.findByName(bookDTO.getAuthor().getName());

        if (authorByName.isEmpty()) {
            AuthorEntity author = new AuthorEntity().setName(bookDTO.getAuthor().getName());
            authorRepository.save(author);
            newBook.setAuthor(author);
        } else {
            newBook.setAuthor(authorByName.get());
        }

        BookEntity bookEntity = bookRepository.save(newBook);

        return bookEntity.getId();
    }

    @Override
    public Long updateBook(BookDTO bookDTO) {
        BookEntity bookEntity = bookRepository.findById(bookDTO.getId())
                .orElse(null);
        if (bookEntity == null) {
            return null;
        }

        AuthorEntity author = authorRepository.
                findByName(bookDTO.getAuthor().getName()).
                orElseGet(() -> {
                    AuthorEntity newAuthor = new AuthorEntity().setName(bookDTO.getAuthor().getName());
                    return authorRepository.save(newAuthor);
                });

        bookEntity.setTitle(bookDTO.getTitle())
                .setIsbn(bookDTO.getIsbn())
                .setAuthor(author);

        return bookRepository.save(bookEntity).getId();

    }

    @Override
    public Page<BookDTO> getBooks(int pageNo, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return bookRepository
                .findAll(pageable)
                .map(this::map);

    }

    private BookDTO map(BookEntity book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        AuthorDTO authorDTO = modelMapper.map(book.getAuthor(), AuthorDTO.class);
        bookDTO.setAuthor(authorDTO);
        return bookDTO;
    }
}
