package com.example.andreys.service;

import com.example.andreys.model.service.ItemServiceModel;
import com.example.andreys.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {
    boolean existsByName(String name);

    void addItem(ItemServiceModel itemServiceModel);

    List<ItemViewModel> findAllItems();

    ItemViewModel findItemById(Long id);

    void deleteItem(Long id);
}
