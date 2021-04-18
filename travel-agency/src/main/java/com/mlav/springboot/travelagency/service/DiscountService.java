package com.mlav.springboot.travelagency.service;

import com.mlav.springboot.travelagency.dto.DiscountDto;

public interface DiscountService {
    DiscountDto getDiscount();

    DiscountDto updateDiscount(DiscountDto discountDto);
}
