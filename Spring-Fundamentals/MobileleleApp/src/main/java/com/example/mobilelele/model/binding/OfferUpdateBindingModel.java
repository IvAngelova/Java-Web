package com.example.mobilelele.model.binding;

import com.example.mobilelele.model.entity.enums.EngineEnum;
import com.example.mobilelele.model.entity.enums.TransmissionEnum;

import javax.validation.constraints.*;


public class OfferUpdateBindingModel {
    private Long id;
    @NotBlank
    private String description;
    @NotNull
    private EngineEnum engine;
    @NotBlank
    private String imageUrl;
    @PositiveOrZero
    @NotNull
    private Integer mileage;
    @NotNull
    @Min(100)
    private Integer price;
    @NotNull
    private TransmissionEnum transmission;
    @NotNull
    @Min(1930)
    private Integer year;

    public OfferUpdateBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public OfferUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferUpdateBindingModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferUpdateBindingModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OfferUpdateBindingModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferUpdateBindingModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferUpdateBindingModel setYear(Integer year) {
        this.year = year;
        return this;
    }
}
