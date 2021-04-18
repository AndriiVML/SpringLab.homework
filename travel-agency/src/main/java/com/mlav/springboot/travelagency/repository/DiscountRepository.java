package com.mlav.springboot.travelagency.repository;

import com.mlav.springboot.travelagency.model.entity.Discount;

public interface DiscountRepository {
    Discount getDiscount();

    Discount updateDiscount(Discount discount1);
}
