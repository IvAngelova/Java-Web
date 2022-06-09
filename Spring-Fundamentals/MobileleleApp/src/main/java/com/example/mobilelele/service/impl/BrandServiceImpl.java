package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.Brand;
import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.view.BrandView;
import com.example.mobilelele.model.view.ModelView;
import com.example.mobilelele.repository.BrandRepository;
import com.example.mobilelele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeBrand() {
        if (brandRepository.count() == 0) {
            Brand ford = new Brand().setName("Ford");
            brandRepository.save(ford);

        }
    }

    @Override
    public List<BrandView> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(brandEntity -> {
                    BrandView brandView = new BrandView().
                            setName(brandEntity.getName());

                    brandView.setModels(
                            brandEntity.
                                    getModels().
                                    stream().
                                    map(this::map).
                                    collect(Collectors.toList()));
                    return brandView;
                })
                .collect(Collectors.toList());
    }

    private ModelView map(Model modelEntity) {
        return modelMapper.map(modelEntity, ModelView.class);
    }
}
