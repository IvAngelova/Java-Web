package com.example.mobilelele.model.view;

import java.util.ArrayList;
import java.util.List;

public class BrandView {
    private String name;
    private List<ModelView> models = new ArrayList<>();

    public BrandView() {
    }

    public String getName() {
        return name;
    }

    public BrandView setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelView> getModels() {
        return models;
    }

    public BrandView setModels(List<ModelView> models) {
        this.models = models;
        return this;
    }

    public BrandView addModel(ModelView model) {
        if (this.models == null) {
            this.models = new ArrayList<>();
        }
        this.models.add(model);
        return this;
    }
}
