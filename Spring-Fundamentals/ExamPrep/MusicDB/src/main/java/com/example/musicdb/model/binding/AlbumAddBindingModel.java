package com.example.musicdb.model.binding;

import com.example.musicdb.model.entity.enums.ArtistNameEnum;
import com.example.musicdb.model.entity.enums.GenreEnum;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddBindingModel {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank
    @Size(min = 5)
    private String imageUrl;
    @NotBlank
    @Size(min = 5)
    private String description;
    @Min(10)
    @NotNull
    private Integer copies;
    @Positive
    @NotNull
    private BigDecimal price;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String producer;
    @NotNull
    private GenreEnum genre;
    @NotNull
    private ArtistNameEnum artist;

    public AlbumAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public AlbumAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumAddBindingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumAddBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AlbumAddBindingModel setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public AlbumAddBindingModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public ArtistNameEnum getArtist() {
        return artist;
    }

    public AlbumAddBindingModel setArtist(ArtistNameEnum artist) {
        this.artist = artist;
        return this;
    }
}
