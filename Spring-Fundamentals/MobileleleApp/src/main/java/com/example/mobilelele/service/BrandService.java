package com.example.mobilelele.service;

import com.example.mobilelele.model.view.BrandView;

import java.util.List;

public interface BrandService {
    void initializeBrand();

    List<BrandView> getAllBrands();
}
