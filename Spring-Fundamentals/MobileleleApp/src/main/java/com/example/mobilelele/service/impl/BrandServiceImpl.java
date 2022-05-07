package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.Brand;
import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.entity.enums.Category;
import com.example.mobilelele.repository.BrandRepository;
import com.example.mobilelele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void initializeBrand() {
        if (brandRepository.count() == 0) {
            Brand ford = new Brand().setName("Ford");
            brandRepository.save(ford);

        }
    }
}
