package com.springboot.homework4.repository.impl;

import com.springboot.homework4.model.Status;
import com.springboot.homework4.model.entity.TourPurchase;
import com.springboot.homework4.model.entity.User;
import com.springboot.homework4.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class OrderRepositoryImpl implements OrderRepository {
    List<TourPurchase> list = new ArrayList<>();


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public TourPurchase getOrder(long id) {
        return list.stream()
                .filter(tp -> tp.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found order with id = " + id));
    }

    @Override
    public List<TourPurchase> getAllOrders() {
        return list;
    }

    @Override
    public List<TourPurchase> getAllOrdersForUser(User user) {
        return list.stream()
                .filter(tp -> tp.getUser().getId() == user.getId())
                .collect(Collectors.toList());
    }

    @Override
    public TourPurchase createOrder(TourPurchase tourPurchase) {
        list.add(tourPurchase);
        return tourPurchase;
    }

    @Override
    public TourPurchase changeStatus(Status status, TourPurchase tourPurchase) {
        Status previousStatus = tourPurchase.getStatus();
        if (status == Status.REGISTERED) {
            throw new RuntimeException(
                    String.format("Cannot change status %s to status %s", status, Status.REGISTERED));
        }
        if (tourPurchase.getStatus() != Status.REGISTERED) {
            throw new RuntimeException("Status's already changed");
        }
        boolean isDeleted = list.removeIf(tp -> tp.getId() == tourPurchase.getId());
        tourPurchase.setStatus(status);
        if (isDeleted) {
            log.info(String.format("Status is changed from %s to %s in order: %s", previousStatus, status, tourPurchase));
            list.add(tourPurchase);
        } else {
            log.error(String.format("Cannot change status of order. Order does not exist %s", tourPurchase));
            throw new RuntimeException("Order does not exist.");
        }
        return tourPurchase;
    }

    @Override
    public void deleteOrder(long id) {
        boolean isDeleted = list.removeIf(tp -> tp.getId() == id);
        if (!isDeleted) {
            throw new RuntimeException("Cannot delete a non-existent order " + id);
        }
    }
}
