package com.springboot.homework4.repository;

import com.springboot.homework4.model.Status;
import com.springboot.homework4.model.entity.TourPurchase;
import com.springboot.homework4.model.entity.User;

import java.util.List;

public interface OrderRepository {
    int getCount();

    TourPurchase getOrder(long id);

    List<TourPurchase> getAllOrders();

    List<TourPurchase> getAllOrdersForUser(User user);

    TourPurchase createOrder(TourPurchase tourPurchase);

    TourPurchase changeStatus(Status status, TourPurchase tourPurchase);

    void deleteOrder(long id);
}
