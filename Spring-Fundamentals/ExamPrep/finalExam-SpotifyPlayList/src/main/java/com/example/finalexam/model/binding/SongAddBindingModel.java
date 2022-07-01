package com.example.finalexam.model.binding;

import com.example.finalexam.model.entity.enums.StyleNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class SongAddBindingModel {
    @NotBlank(message = "Cannot be empty!")
    @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters!")
    private String performer;
    @NotBlank(message = "Cannot be empty!")
    @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters!")
    private String title;
    @Positive(message = "Duration must be positive!")
    @NotNull(message = "Cannot be empty!")
    private Integer duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The date cannot be in the future!")
    private LocalDate releaseDate;
    @NotNull(message = "You must select a style!")
    private StyleNameEnum style;

    public SongAddBindingModel() {
    }

    public String getPerformer() {
        return performer;
    }

    public SongAddBindingModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SongAddBindingModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongAddBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public StyleNameEnum getStyle() {
        return style;
    }

    public SongAddBindingModel setStyle(StyleNameEnum style) {
        this.style = style;
        return this;
    }
}
