package com.mlav.springboot.travelagency.repository;

import com.mlav.springboot.travelagency.model.Status;
import com.mlav.springboot.travelagency.model.entity.TourPurchase;
import com.mlav.springboot.travelagency.model.entity.User;

import java.util.List;

public interface OrderRepository {
    int getCount();

    TourPurchase getOrder(long id);

    List<TourPurchase> getAllOrders();

    List<TourPurchase> getAllOrdersForUser(String login);

    TourPurchase createOrder(TourPurchase tourPurchase);

    TourPurchase changeStatus(Status status, TourPurchase tourPurchase);

    void deleteOrder(long id);
}
