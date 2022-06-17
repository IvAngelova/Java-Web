package com.example.coffee_shop.service.impl;

import com.example.coffee_shop.model.entity.OrderEntity;
import com.example.coffee_shop.model.service.OrderServiceModel;
import com.example.coffee_shop.model.view.OrderViewModel;
import com.example.coffee_shop.repository.OrderRepository;
import com.example.coffee_shop.service.CategoryService;
import com.example.coffee_shop.service.OrderService;
import com.example.coffee_shop.service.UserService;
import com.example.coffee_shop.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity orderEntity = modelMapper.map(orderServiceModel, OrderEntity.class);
        orderEntity.setEmployee(userService.findUserById(currentUser.getId()));
        orderEntity.setCategory(categoryService.findCategoryByName(orderServiceModel.getCategory()));

        OrderEntity saved = orderRepository.save(orderEntity);

        OrderServiceModel orderModel = modelMapper.map(saved, OrderServiceModel.class);
        orderModel.setCategory(orderServiceModel.getCategory());
        return orderModel;
    }

    @Override
    public List<OrderViewModel> findAllOrdersOrderByPriceDesc() {

        return orderRepository
                .findAllByOrderByPriceDesc()
                .stream()
                .map(orderEntity -> {
                    return modelMapper.map(orderEntity, OrderViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
