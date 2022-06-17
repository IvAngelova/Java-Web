package com.example.coffee_shop.service;

import com.example.coffee_shop.model.service.OrderServiceModel;
import com.example.coffee_shop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    OrderServiceModel addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrdersOrderByPriceDesc();

    void deleteOrder(Long id);
}
