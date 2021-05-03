package com.mlav.springboot.travelagency.service.impl;

import com.mlav.springboot.travelagency.dto.OrderDto;
import com.mlav.springboot.travelagency.dto.TourPurchaseDto;
import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.exception.*;
import com.mlav.springboot.travelagency.model.Status;
import com.mlav.springboot.travelagency.model.entity.Discount;
import com.mlav.springboot.travelagency.model.entity.Tour;
import com.mlav.springboot.travelagency.model.entity.TourPurchase;
import com.mlav.springboot.travelagency.model.entity.User;
import com.mlav.springboot.travelagency.repository.DiscountRepository;
import com.mlav.springboot.travelagency.repository.OrderRepository;
import com.mlav.springboot.travelagency.repository.TourRepository;
import com.mlav.springboot.travelagency.repository.UserRepository;
import com.mlav.springboot.travelagency.service.OrderService;
import com.mlav.springboot.travelagency.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
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
//                .user(userRepository.getUser(orderDto.getUserLogin()))
//                .tour(tourRepository.getTour(orderDto.getTourId()))
                .actualPrice(orderDto.getActualPrice())
                .dateTimeOfPurchase(orderDto.getDateTimeOfPurchase())
                .statusId(orderDto.getStatus().ordinal())
                .numberOfTours(orderDto.getNumberOfTours())
                .build();
        tourPurchase.setId(orderDto.getId());
        return tourPurchase;
    }

    private OrderDto mapTourPurchaseToOrderDto(TourPurchase tourPurchase) {
        return OrderDto.builder()
                .tourId(tourPurchase.getTour().getId())
                .userLogin(tourPurchase.getUser().getAccount().getLogin())
                .actualPrice(tourPurchase.getActualPrice())
                .dateTimeOfPurchase(tourPurchase.getDateTimeOfPurchase())
                .status(Status.getStatus(tourPurchase))
                .numberOfTours(tourPurchase.getNumberOfTours())
                .id(tourPurchase.getId())
                .build();
    }

    @Override
    public OrderDto getOrder(long id) {
        log.info("Attempt to get order with id=" + id);
        TourPurchase tourPurchase = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        log.info("Order: {}", tourPurchase);
        return mapTourPurchaseToOrderDto(tourPurchase);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        log.info("Attempt to get all orders");
        List<TourPurchase> orders = orderRepository.findAll();
        for (TourPurchase tp : orders) {
            orderDtos.add(mapTourPurchaseToOrderDto(tp));
        }
        log.info("All orders: {}", orders);
        return orderDtos;
    }

    @Override
    public List<OrderDto> findPaginated(int pageNumber, int pageSize, List<Sort.Order> orders) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(orders));
        List<OrderDto> orderDtos = new ArrayList<>();
        for (TourPurchase tp : orderRepository.findAll(paging).toList()) {
            orderDtos.add(mapTourPurchaseToOrderDto(tp));
        }
        return orderDtos;
    }

    @Override
    public List<OrderDto> getUserOrders(String login) {
        log.info("Attempt to get all orders for user with login=" + login);
        User user = userRepository.findByAccount_Login(login)
                .orElseThrow(() -> new UserNotFoundException("Cannot find orders for user. User does not exist"));
        List<OrderDto> orderDtos = new ArrayList<>();
        List<TourPurchase> userOrders = orderRepository.findAllByUser(user);
        for (TourPurchase tp : userOrders) {
            orderDtos.add(mapTourPurchaseToOrderDto(tp));
        }
        log.info("All orders for user with login={}: {}", login, userOrders);
        return orderDtos;
    }

    @Override
    @Transactional(rollbackOn = SQLException.class)
    public OrderDto orderTour(OrderDto orderDto) {
        //
        //
        //QUANITYT 0 - OKAY, -1 SERVER EROR - FATAL ERROR NEED FIX
        //
        //
        Tour tour = tourRepository.findById(orderDto.getTourId())
                .orElseThrow(() -> new TourNotFoundException("Cannot order tour. Tour does not exist"));
        User user = userRepository.findByAccount_Login(orderDto.getUserLogin())
                .orElseThrow(() -> new UserNotFoundException("Cannot order tour. User does not exist"));
        TourPurchase tourPurchase = TourPurchase.builder()
                .user(user)
                .tour(tour)
                .numberOfTours(orderDto.getNumberOfTours())
                .actualPrice(Util.getActualPrice(tour.getPrice(), user.getDiscount(), orderDto.getNumberOfTours()))
                .statusId(Status.REGISTERED.ordinal())
                .dateTimeOfPurchase(LocalDateTime.now())
                .build();
        tourPurchase.setId(Util.generateUniqueId());
        log.info("Attempt to make order: " + tourPurchase);

        tourPurchase = orderRepository.save(tourPurchase);
        log.info("New order: {}", tourPurchase);
//        log.info("Attempt to decrease number of tours in tour:{}", tour);
//        tour.setNumberOfTours(tourPurchase.getNumberOfTours() - tour.getNumberOfTours());
//        tourRepository.save(tour);
//        log.info("Tour is updated");
        log.info(String.format("Attempt to increase discount of user: %s after ordering tour", user));
        user = updateUserDiscount(user);
        userRepository.save(user);
        log.info("User is updated");
        return mapTourPurchaseToOrderDto(tourPurchase);
    }

    private User updateUserDiscount(User user) {
        Discount discount = discountRepository.findById(Util.DISCOUNT_ID).orElseThrow(DiscountNotFoundException::new);
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
        log.info(String.format("Attempt to change user discount from %d to %d", currentDiscount, user.getDiscount()));
        return user;
    }

    @Override
    public void deleteOrder(long id) {
        log.info("Attempt to delete order with id=" + id);
        TourPurchase order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Cannot delete a non-existent order"));
        orderRepository.delete(order);
        log.info("Order is deleted");
    }

    @Override
    public OrderDto changeStatus(Status status, OrderDto orderDto) {
        log.info(String.format("Attempt to change order status, order: %s possibleStatus: %s", orderDto, status));
        TourPurchase order = mapOrderDtoToTourPurchase(orderDto);
        validateStatus(status, order);
        Status previousStatus = Status.getStatus(order);
        order.setStatusId(status.ordinal());
        order = orderRepository.save(order);
        log.info(String.format("Status is changed from %s to %s in order: %s", previousStatus, status, order));
        return mapTourPurchaseToOrderDto(order);
    }

    private void validateStatus(Status status, TourPurchase tourPurchase) {
        if (status == Status.REGISTERED) {
            throw new NotValidStatusException(
                    String.format("Cannot change status %s to status %s",
                            Status.getStatus(tourPurchase), Status.REGISTERED));
        }
        if (Status.getStatus(tourPurchase) != Status.REGISTERED) {
            throw new NotValidStatusException("Status is already changed");
        }
    }

}
