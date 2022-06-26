package com.example.andreys.service.impl;

import com.example.andreys.model.entity.ItemEntity;
import com.example.andreys.model.service.ItemServiceModel;
import com.example.andreys.model.view.ItemViewModel;
import com.example.andreys.repository.ItemRepository;
import com.example.andreys.service.CategoryService;
import com.example.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public boolean existsByName(String name) {
        return itemRepository.existsByName(name);
    }

    @Override
    public void addItem(ItemServiceModel itemServiceModel) {
        ItemEntity itemEntity = modelMapper.map(itemServiceModel, ItemEntity.class);
        itemEntity.setCategory(categoryService.findCategoryByName(itemServiceModel.getCategory()));
        itemRepository.save(itemEntity);
    }

    @Override
    public List<ItemViewModel> findAllItems() {
        return itemRepository
                .findAll()
                .stream()
                .map(this::mapFromEntityToViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewModel findItemById(Long id) {
        return itemRepository.findById(id)
                .map(this::mapFromEntityToViewModel)
                .orElse(null);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemViewModel mapFromEntityToViewModel(ItemEntity itemEntity) {
        ItemViewModel itemViewModel = modelMapper.map(itemEntity, ItemViewModel.class);
        itemViewModel.setCategory(itemEntity.getCategory().getName());
        return itemViewModel;
    }


}
