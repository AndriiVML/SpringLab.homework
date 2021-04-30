package com.mlav.springboot.travelagency.controller;

import com.mlav.springboot.travelagency.api.DiscountApi;
import com.mlav.springboot.travelagency.controller.assembler.DiscountAssembler;
import com.mlav.springboot.travelagency.controller.model.DiscountModel;
import com.mlav.springboot.travelagency.dto.DiscountDto;
import com.mlav.springboot.travelagency.exception.DiscountPatchException;
import com.mlav.springboot.travelagency.service.DiscountService;
import com.mlav.springboot.travelagency.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequiredArgsConstructor
public class DiscountController implements DiscountApi {
    private final DiscountService discountService;
    private final DiscountAssembler discountAssembler;

    @Override
    public DiscountModel getDiscount() {
        log.info("Attempt to get general discount");
        DiscountDto entity = discountService.getDiscount(Util.DISCOUNT_ID);
        return discountAssembler.toModel(entity);
    }

    /*
    * testing put:
    *  {
            "step": 5,
            "max": 20
       }
    * */

    @Override
    public DiscountModel updateDiscount(DiscountDto discountDto) {
        log.info("Attempt to change discount: " + discountDto);
        DiscountDto entity = discountService.updateDiscount(discountDto);
        return discountAssembler.toModel(entity);
    }


    @Override
    public DiscountDto applyPatchToDiscount(DiscountDto discountDto) {
        log.info("Attempt to change discount: " + discountDto);
        DiscountDto discountFromDb = discountService.getDiscount(Util.DISCOUNT_ID);
        if (discountDto.getStep() == null) {
            discountDto.setStep(discountFromDb.getStep());
        }
        if (discountDto.getMax() == null) {
            discountDto.setMax(discountFromDb.getMax());
        }
        discountDto.setId(discountFromDb.getId());

        Set<ConstraintViolation<DiscountDto>> errors =
                Validation.buildDefaultValidatorFactory().getValidator().validate(discountDto);

        List<String> errorMessages = errors.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        if (!errorMessages.isEmpty()) {
            throw new DiscountPatchException(errorMessages);
        }
        return discountService.updateDiscount(discountDto);
    }


}
