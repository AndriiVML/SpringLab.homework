package com.springboot.homework4.service.impl;

import com.springboot.homework4.model.Status;
import com.springboot.homework4.model.entity.Discount;
import com.springboot.homework4.model.entity.Tour;
import com.springboot.homework4.model.entity.TourPurchase;
import com.springboot.homework4.model.entity.User;
import com.springboot.homework4.repository.DiscountRepository;
import com.springboot.homework4.repository.OrderRepository;
import com.springboot.homework4.repository.TourRepository;
import com.springboot.homework4.repository.UserRepository;
import com.springboot.homework4.service.OrderService;
import com.springboot.homework4.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final DiscountRepository discountRepository;

    @Override
    public TourPurchase getOrder(long id) {
        log.info("Attempt to get order with id=" + id);
        return orderRepository.getOrder(id);
    }

    @Override
    public List<TourPurchase> getAllOrders() {
        log.info("Attempt to get all orders");
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
        log.info("Attempt to make order: " + tourPurchase);
        tourPurchase = orderRepository.createOrder(tourPurchase);
        log.info(String.format("Attempt to increase discount of user: %s after ordering tour", user));
        user = updateUserDiscount(user);
        userRepository.updateUser(userLogin, user);
        return tourPurchase;
    }

    private User updateUserDiscount(User user) {
        Discount discount = discountRepository.getDiscount();
        int currentDiscount = user.getDiscount();
        if (currentDiscount == discount.getMax()) {
            log.info("User discount is already maximum");
            return user;
        }
        int possibleDiscount = currentDiscount + discount.getStep();
        if (possibleDiscount > discount.getMax()) {
            user.setDiscount(discount.getMax());
        } else {
            user.setDiscount(possibleDiscount);
        }
        log.info(String.format("User discount is changed from %d to %d", currentDiscount, user.getDiscount()));
        return user;
    }

    @Override
    public void deleteOrder(long id) {
        log.info("Attempt to delete order with id=" + id);
        orderRepository.deleteOrder(id);
    }

    @Override
    public TourPurchase changeStatus(Status status, TourPurchase tourPurchase) {
        log.info(String.format("Attempt to change order status, order: %s possibleStatus: %s",tourPurchase,status));
        return orderRepository.changeStatus(status, tourPurchase);
    }

}
