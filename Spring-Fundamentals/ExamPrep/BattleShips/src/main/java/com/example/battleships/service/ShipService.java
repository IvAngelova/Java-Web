package com.example.battleships.service;

import com.example.battleships.model.binding.HomeBindingModel;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.model.view.ShipViewModel;

import java.util.List;

public interface ShipService {
    boolean existsByName(String name);

    void addShip(ShipServiceModel shipServiceModel);

    List<ShipViewModel> findAllShipsOrderByStatus();

    void fight(HomeBindingModel homeBindingModel);
}
