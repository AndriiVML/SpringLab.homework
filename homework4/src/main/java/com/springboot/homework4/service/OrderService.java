package com.springboot.homework4.service;

import com.springboot.homework4.dto.OrderDto;
import com.springboot.homework4.model.Status;
import com.springboot.homework4.model.entity.TourPurchase;

import java.util.List;

public interface OrderService {
    OrderDto getOrder(long id);

    List<OrderDto> getAllOrders();

    OrderDto orderTour(OrderDto orderDto);

    void deleteOrder(long id);

    OrderDto changeStatus(Status status, OrderDto orderDto);
}
