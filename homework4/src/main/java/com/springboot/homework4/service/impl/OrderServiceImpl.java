package com.springboot.homework4.service.impl;

import com.springboot.homework4.model.Status;
import com.springboot.homework4.model.entity.Tour;
import com.springboot.homework4.model.entity.TourPurchase;
import com.springboot.homework4.model.entity.User;
import com.springboot.homework4.repository.OrderRepository;
import com.springboot.homework4.repository.TourRepository;
import com.springboot.homework4.repository.UserRepository;
import com.springboot.homework4.service.OrderService;
import com.springboot.homework4.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    @Override
    public TourPurchase getOrder(long id) {
        return orderRepository.getOrder(id);
    }

    @Override
    public List<TourPurchase> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public TourPurchase orderTour(long tourId, String userLogin, int quantity) {
        Tour tour = tourRepository.getTour(tourId);
        User user = userRepository.getUser(userLogin);
        TourPurchase tourPurchase = TourPurchase.builder()
                .user(user)
                .tour(tour)
                .numberOfTours(quantity)
                .actualPrice(Util.getActualPrice(tour.getPrice(), user.getDiscount(), quantity))
                .status(Status.REGISTERED)
                .dateTimeOfPurchase(LocalDateTime.now())
                .build();
        tourPurchase.setId(Util.generateUniqueId());
        tourPurchase = orderRepository.createOrder(tourPurchase);
        return tourPurchase;
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteOrder(id);
    }

    @Override
    public TourPurchase changeStatus(Status status, TourPurchase tourPurchase) {
        return orderRepository.changeStatus(status, tourPurchase);
    }

}
