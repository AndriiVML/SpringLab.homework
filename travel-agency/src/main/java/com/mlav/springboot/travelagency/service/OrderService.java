package com.mlav.springboot.travelagency.service;

import com.mlav.springboot.travelagency.dto.OrderDto;
import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.model.Status;
import com.mlav.springboot.travelagency.model.entity.TourPurchase;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    OrderDto getOrder(long id);

    List<OrderDto> getAllOrders();

    List<OrderDto> findPaginatedAndSorted(int pageNumber, int pageSize, List<Sort.Order> orders);

    List<OrderDto> getUserOrders(String login);

    OrderDto orderTour(OrderDto orderDto);

    void deleteOrder(long id);

    OrderDto changeStatus(Status status, OrderDto orderDto);
}
