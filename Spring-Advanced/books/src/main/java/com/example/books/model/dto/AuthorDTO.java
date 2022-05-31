package com.example.books.model.dto;

import java.util.List;

public class AuthorDTO {

    private String name;
    // private List<BookDTO> books;

    public AuthorDTO() {
    }

    public String getName() {
        return name;
    }

    public AuthorDTO setName(String name) {
        this.name = name;
        return this;
    }
}
