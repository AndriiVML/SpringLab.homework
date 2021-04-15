package com.springboot.homework4.repository.impl;

import com.springboot.homework4.model.Status;
import com.springboot.homework4.model.entity.TourPurchase;
import com.springboot.homework4.model.entity.User;
import com.springboot.homework4.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {
    List<TourPurchase> list = new ArrayList<>();


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public TourPurchase getOrder(long id) {
        TourPurchase tourPurchase = list.stream()
                .filter(tp -> tp.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order is not found"));
        log.info("order: " + tourPurchase);
        return tourPurchase;
    }

    @Override
    public List<TourPurchase> getAllOrders() {
        log.info("all orders: " + list);
        return list;
    }

    @Override
    public List<TourPurchase> getAllOrdersForUser(User user) {
        List<TourPurchase> result = list.stream()
                .filter(tp -> tp.getUser().getId() == user.getId())
                .collect(Collectors.toList());
        log.info("all orders for user {}: {}", user, result);
        return result;
    }

    @Override
    public TourPurchase createOrder(TourPurchase tourPurchase) {
        list.add(tourPurchase);
        log.info("New order: " + tourPurchase);
        return tourPurchase;
    }

    @Override
    public TourPurchase changeStatus(Status status, TourPurchase tourPurchase) {
        Status previousStatus = tourPurchase.getStatus();
        if (status == Status.REGISTERED) {
            log.error("Cannot change status {} to status {}", status, Status.REGISTERED);
            throw new RuntimeException(
                    String.format("Cannot change status %s to status %s", status, Status.REGISTERED));
        }
        if (tourPurchase.getStatus() != Status.REGISTERED) {
            String message2 = "Status is already changed";
            log.error(message2);
            throw new RuntimeException(message2);
        }
        boolean isDeleted = list.removeIf(tp -> tp.getId() == tourPurchase.getId());
        tourPurchase.setStatus(status);
        if (isDeleted) {
            list.add(tourPurchase);
        } else {
            String message3 = "Cannot change status of order. Order does not exist ";
            log.error(message3);
            throw new RuntimeException(message3);
        }
        log.info(String.format("Status is changed from %s to %s in order: %s", previousStatus, status, tourPurchase));
        return tourPurchase;
    }

    @Override
    public void deleteOrder(long id) {
        boolean isDeleted = list.removeIf(tp -> tp.getId() == id);
        if (!isDeleted) {
            throw new RuntimeException("Cannot delete a non-existent order");
        }
        log.info("Order is deleted");
    }
}
