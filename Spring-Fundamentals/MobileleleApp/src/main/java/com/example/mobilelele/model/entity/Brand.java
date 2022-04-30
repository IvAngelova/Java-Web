package com.example.mobilelele.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", targetEntity = Model.class, cascade = CascadeType.ALL)
    private List<Model> models;

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public Brand setName(String name) {
        this.name = name;
        return this;
    }

    public List<Model> getModels() {
        return models;
    }

    public Brand setModels(List<Model> models) {
        this.models = models;
        return this;
    }
}
