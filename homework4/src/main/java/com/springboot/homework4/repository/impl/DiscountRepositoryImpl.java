package com.springboot.homework4.repository.impl;

import com.springboot.homework4.model.entity.Discount;
import com.springboot.homework4.repository.DiscountRepository;
import org.springframework.stereotype.Component;

@Component
public class DiscountRepositoryImpl implements DiscountRepository {
    private final Discount discount = new Discount();

    @Override
    public Discount getDiscount() {
        return discount;
    }

    @Override
    public Discount updateDiscount(int step, int max) {
        if (step > max) {
            throw new RuntimeException(String.format("Step: %d can't be more than max: %d", step, max));
        }
        if (step < 0 || step > 100) {
            throw new RuntimeException(String.format("Step: %d is not in range [0;100]", step));
        }
        if (max > 100 || max < 0) {
            throw new RuntimeException(String.format("Max: %d is not in range [0;100]", max));
        }
        discount.setStep(step);
        discount.setMax(max);
        return discount;
    }

}
