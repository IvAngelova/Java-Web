package com.example.musicdb.model.entity;

import com.example.musicdb.model.entity.enums.GenreEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imageUrl;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer copies;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private LocalDate releaseDate;
    private String producer;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private GenreEnum genre;
    @ManyToOne(optional = false)
    private ArtistEntity artist;
    @ManyToOne(optional = false)
    private UserEntity addedFrom;

    public AlbumEntity() {
    }

    public String getName() {
        return name;
    }

    public AlbumEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumEntity setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AlbumEntity setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public AlbumEntity setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public AlbumEntity setArtist(ArtistEntity artist) {
        this.artist = artist;
        return this;
    }

    public UserEntity getAddedFrom() {
        return addedFrom;
    }

    public AlbumEntity setAddedFrom(UserEntity addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }
}
