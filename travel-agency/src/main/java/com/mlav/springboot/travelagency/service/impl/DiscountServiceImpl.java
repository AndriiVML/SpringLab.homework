package com.mlav.springboot.travelagency.service.impl;

import com.mlav.springboot.travelagency.dto.DiscountDto;
import com.mlav.springboot.travelagency.exception.DiscountNotFoundException;
import com.mlav.springboot.travelagency.model.entity.Discount;
import com.mlav.springboot.travelagency.repository.DiscountRepository;
import com.mlav.springboot.travelagency.service.DiscountService;
import com.mlav.springboot.travelagency.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    private Discount mapDiscountDtoToDiscount(DiscountDto discountDto) {
        return Discount.builder()
                .id(Util.DISCOUNT_ID)
                .max(discountDto.getMax())
                .step(discountDto.getStep())
                .build();
    }

    private DiscountDto mapDiscountToDiscountDto(Discount discount) {
        return DiscountDto.builder()
                .max(discount.getMax())
                .step(discount.getStep())
                .id(discount.getId())
                .build();
    }

    @Override
    public DiscountDto getDiscount(Long id) {
        log.info("Attempt to get general discount");
        Discount discount = discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
        log.info("discount: {}",discount);
        return mapDiscountToDiscountDto(discount);
    }

    @Override
    public DiscountDto updateDiscount(DiscountDto discountDto) {
        log.info("Attempt to set general discount: {}", discountDto);
        discountDto.setId(Util.DISCOUNT_ID);
        Discount discount = discountRepository.save(mapDiscountDtoToDiscount(discountDto));
        log.info("Discount is changed: {}",discount);
        return mapDiscountToDiscountDto(discount);
    }


}
