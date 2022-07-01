package com.example.finalexam.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "songs")
public class SongEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String performer;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer duration;
    @Column
    private LocalDate releaseDate;
    @ManyToOne(optional = false)
    private StyleEntity style;
    @ManyToMany(mappedBy = "playlist", fetch = FetchType.EAGER)
    private List<UserEntity> users;

    public SongEntity() {
    }

    public String getPerformer() {
        return performer;
    }

    public SongEntity setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SongEntity setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public StyleEntity getStyle() {
        return style;
    }

    public SongEntity setStyle(StyleEntity style) {
        this.style = style;
        return this;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public SongEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }
}
