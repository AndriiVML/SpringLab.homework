package com.springboot.homework4.repository;

import com.springboot.homework4.model.entity.Discount;

public interface DiscountRepository {
    Discount getDiscount();

    Discount updateDiscount(int step, int max);
}
