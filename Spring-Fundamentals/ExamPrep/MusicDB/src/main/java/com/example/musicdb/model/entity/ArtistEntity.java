package com.example.musicdb.model.entity;

import com.example.musicdb.model.entity.enums.ArtistNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArtistNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String careerInformation;

    public ArtistEntity() {
    }

    public ArtistNameEnum getName() {
        return name;
    }

    public ArtistEntity setName(ArtistNameEnum name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public ArtistEntity setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
