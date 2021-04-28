package com.mlav.springboot.travelagency.model;

import com.mlav.springboot.travelagency.model.entity.TourPurchase;

public enum Status {
    REGISTERED, PAID, CANCELLED;

    public static Status getStatus(TourPurchase tourPurchase) {
        int id = tourPurchase.getStatusId();
        return Status.values()[id];
    }

    public String getName() {
        return name().toUpperCase();
    }
}
