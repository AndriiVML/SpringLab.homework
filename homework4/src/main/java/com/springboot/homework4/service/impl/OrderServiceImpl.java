package com.springboot.homework4.service.impl;

import com.springboot.homework4.dto.OrderDto;
import com.springboot.homework4.dto.TourPurchaseDto;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final DiscountRepository discountRepository;

//
//    private TourPurchaseDto mapTourPurchaseToTourPurchaseDto(TourPurchase tourPurchase) {
//        return TourPurchaseDto.builder()
//                .user(tourPurchase.getUser())
//                .tour(tourPurchase.getTour())
//                .actualPrice(tourPurchase.getActualPrice())
//                .dateTimeOfPurchase(tourPurchase.getDateTimeOfPurchase())
//                .status(tourPurchase.getStatus())
//                .numberOfTours(tourPurchase.getNumberOfTours())
//                .id(tourPurchase.getId())
//                .build();
//    }
//
//    private TourPurchase mapTourPurchaseDtoToTourPurchase(TourPurchaseDto tourPurchaseDto) {
//        TourPurchase tourPurchase = TourPurchase.builder()
//                .user(tourPurchaseDto.getUser())
//                .tour(tourPurchaseDto.getTour())
//                .actualPrice(tourPurchaseDto.getActualPrice())
//                .dateTimeOfPurchase(tourPurchaseDto.getDateTimeOfPurchase())
//                .status(tourPurchaseDto.getStatus())
//                .numberOfTours(tourPurchaseDto.getNumberOfTours())
//                .build();
//        tourPurchase.setId(tourPurchaseDto.getId());
//        return tourPurchase;
//    }

    private TourPurchase mapOrderDtoToTourPurchase(OrderDto orderDto) {
        TourPurchase tourPurchase = TourPurchase.builder()
                .user(userRepository.getUser(orderDto.getUserLogin()))
                .tour(tourRepository.getTour(orderDto.getTourId()))
                .actualPrice(orderDto.getActualPrice())
                .dateTimeOfPurchase(orderDto.getDateTimeOfPurchase())
                .status(orderDto.getStatus())
                .numberOfTours(orderDto.getNumberOfTours())
                .build();
        tourPurchase.setId(orderDto.getId());
        return tourPurchase;
    }

    private OrderDto mapTourPurchaseToOrderDto(TourPurchase tourPurchase) {
        return OrderDto.builder()
                .tourId(tourPurchase.getTour().getId())
                .userLogin(tourPurchase.getUser().getLogin())
                .actualPrice(tourPurchase.getActualPrice())
                .dateTimeOfPurchase(tourPurchase.getDateTimeOfPurchase())
                .status(tourPurchase.getStatus())
                .numberOfTours(tourPurchase.getNumberOfTours())
                .id(tourPurchase.getId())
                .build();
    }

    @Override
    public OrderDto getOrder(long id) {
        log.info("Attempt to get order with id=" + id);
        return mapTourPurchaseToOrderDto(orderRepository.getOrder(id));
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (TourPurchase tp : orderRepository.getAllOrders()) {
            orderDtos.add(mapTourPurchaseToOrderDto(tp));
        }
        log.info("Attempt to get all orders");
        return orderDtos;
    }

    @Override
    public OrderDto orderTour(OrderDto orderDto) {
        Tour tour = tourRepository.getTour(orderDto.getTourId());
        User user = userRepository.getUser(orderDto.getUserLogin());
        TourPurchase tourPurchase = TourPurchase.builder()
                .user(user)
                .tour(tour)
                .numberOfTours(orderDto.getNumberOfTours())
                .actualPrice(Util.getActualPrice(tour.getPrice(), user.getDiscount(), orderDto.getNumberOfTours()))
                .status(Status.REGISTERED)
                .dateTimeOfPurchase(LocalDateTime.now())
                .build();
        tourPurchase.setId(Util.generateUniqueId());
        log.info("Attempt to make order: " + tourPurchase);
        tourPurchase = orderRepository.createOrder(tourPurchase);
        log.info(String.format("Attempt to increase discount of user: %s after ordering tour", user));
        user = updateUserDiscount(user);
        userRepository.updateUser(orderDto.getUserLogin(), user);
        return mapTourPurchaseToOrderDto(tourPurchase);
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
    public OrderDto changeStatus(Status status, OrderDto tourPurchase) {
        log.info(String.format("Attempt to change order status, order: %s possibleStatus: %s", tourPurchase, status));
        return mapTourPurchaseToOrderDto(orderRepository.changeStatus(status,
                mapOrderDtoToTourPurchase(tourPurchase)));
    }

}
