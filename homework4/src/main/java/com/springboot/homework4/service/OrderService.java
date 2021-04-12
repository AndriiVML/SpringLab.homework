package com.springboot.homework4.service;

import com.springboot.homework4.model.Status;
import com.springboot.homework4.model.entity.TourPurchase;

import java.util.List;

public interface OrderService {
    TourPurchase getOrder(long id);

    List<TourPurchase> getAllOrders();

    TourPurchase orderTour(long tourId, String userLogin, int quantity);

    void deleteOrder(long id);

    TourPurchase changeStatus(Status status, TourPurchase tourPurchase);
}
