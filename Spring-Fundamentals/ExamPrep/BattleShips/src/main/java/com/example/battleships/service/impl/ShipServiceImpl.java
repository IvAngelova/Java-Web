package com.example.battleships.service.impl;

import com.example.battleships.model.binding.HomeBindingModel;
import com.example.battleships.model.entity.ShipEntity;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.model.service.UserServiceModel;
import com.example.battleships.model.view.ShipViewModel;
import com.example.battleships.repository.ShipRepository;
import com.example.battleships.service.CategoryService;
import com.example.battleships.service.ShipService;
import com.example.battleships.service.UserService;
import com.example.battleships.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;

    public ShipServiceImpl(ShipRepository shipRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService, CategoryService categoryService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public boolean existsByName(String name) {
        return shipRepository.existsByName(name);

    }

    @Override
    public void addShip(ShipServiceModel shipServiceModel) {
        ShipEntity shipEntity = modelMapper.map(shipServiceModel, ShipEntity.class);

        shipEntity.setCategory(categoryService.findByCategoryName(shipServiceModel.getCategory()));
        shipEntity.setUser(userService.findById(currentUser.getId()));

        shipRepository.save(shipEntity);

    }

    @Override
    public List<ShipViewModel> findAllShipsOrderByStatus() {

        return shipRepository.findAllByOrderByStatus()
                .stream()
                .map(shipEntity -> {
                    ShipViewModel shipViewModel = modelMapper.map(shipEntity, ShipViewModel.class);
                    UserServiceModel userServiceModel = modelMapper.map(shipEntity.getUser(), UserServiceModel.class);
                    shipViewModel.setUser(userServiceModel);
                    return shipViewModel;
                })
                .collect(Collectors.toList());


    }

    @Override
    public void fight(HomeBindingModel homeBindingModel) {

        ShipEntity attacker = shipRepository.findById(homeBindingModel.getAttackerShipId()).get();
        ShipEntity defender = shipRepository.findById(homeBindingModel.getDefenderShipId()).get();

        defender.setHealth(defender.getHealth() - attacker.getPower());
        if (defender.getHealth() <= 0) {
            shipRepository.deleteById(homeBindingModel.getDefenderShipId());
        } else {
            shipRepository.save(defender);
        }

    }
}
