package com.mlav.springboot.travelagency.repository.impl;

import com.mlav.springboot.travelagency.exception.NotValidStatusException;
import com.mlav.springboot.travelagency.exception.OrderNotFoundException;
import com.mlav.springboot.travelagency.model.Status;
import com.mlav.springboot.travelagency.model.entity.TourPurchase;
import com.mlav.springboot.travelagency.model.entity.User;
import com.mlav.springboot.travelagency.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//
//@Component
//@Slf4j
//public class OrderRepositoryImpl implements OrderRepository {
public class OrderRepositoryImpl  {
    List<TourPurchase> list = new ArrayList<>();
//
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public TourPurchase getOrder(long id) {
//        TourPurchase tourPurchase = list.stream()
//                .filter(tp -> tp.getId() == id)
//                .findFirst()
//                .orElseThrow(OrderNotFoundException::new);
//        log.info("order: " + tourPurchase);
//        return tourPurchase;
//    }
//
//    @Override
//    public List<TourPurchase> getAllOrders() {
//        log.info("all orders: " + list);
//        return list;
//    }
//
//    @Override
//    public List<TourPurchase> getAllOrdersForUser(String login) {
//        List<TourPurchase> result = list.stream()
//                .filter(tp -> tp.getUser().getAccount().getLogin().equals(login))
//                .collect(Collectors.toList());
//        log.info("all orders for user with login={}: {}", login, result);
//        return result;
//    }
//
//    @Override
//    public TourPurchase createOrder(TourPurchase tourPurchase) {
//        list.add(tourPurchase);
//        log.info("New order: " + tourPurchase);
//        return tourPurchase;
//    }
//
//    @Override
//    public TourPurchase changeStatus(Status status, TourPurchase tourPurchase) {
//        Status previousStatus = tourPurchase.getStatus();
//        if (status == Status.REGISTERED) {
//            throw new NotValidStatusException(
//                    String.format("Cannot change status %s to status %s", tourPurchase.getStatus(), Status.REGISTERED));
//        }
//        if (tourPurchase.getStatus() != Status.REGISTERED) {
//            throw new NotValidStatusException("Status is already changed");
//        }
//        boolean isDeleted = list.removeIf(tp -> tp.getId() == tourPurchase.getId());
//        tourPurchase.setStatus(status);
//        if (isDeleted) {
//            list.add(tourPurchase);
//        } else {
//            throw new NotValidStatusException("Cannot change status of order. Order does not exist ");
//        }
//        log.info(String.format("Status is changed from %s to %s in order: %s", previousStatus, status, tourPurchase));
//        return tourPurchase;
//    }
//
//    @Override
//    public void deleteOrder(long id) {
//        boolean isDeleted = list.removeIf(tp -> tp.getId() == id);
//        if (!isDeleted) {
//            throw new OrderNotFoundException("Cannot delete a non-existent order");
//        }
//        log.info("Order is deleted");
//    }
}
