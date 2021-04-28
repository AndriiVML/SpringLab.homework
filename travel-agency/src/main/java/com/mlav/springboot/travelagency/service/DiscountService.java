package com.mlav.springboot.travelagency.service;

import com.mlav.springboot.travelagency.dto.DiscountDto;

public interface DiscountService {

    DiscountDto getDiscount(Long id);

    DiscountDto updateDiscount(DiscountDto discountDto);
}
