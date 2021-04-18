package com.mlav.springboot.travelagency.controller;

import com.mlav.springboot.travelagency.dto.DiscountDto;
import com.mlav.springboot.travelagency.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping("/discount")
    public DiscountDto getDiscount() {
        log.info("Attempt to get general discount");
        return discountService.getDiscount();
    }

    /*
    * testing put:
    *  {
            "step": 5,
            "max": 20
       }
    * */

    @PutMapping("/discount")
    public DiscountDto updateDiscount(@Valid @RequestBody DiscountDto discountDto) {
        log.info("Attempt to change discount: " + discountDto);
        return discountService.updateDiscount(discountDto);
    }

    /*
    * Do not know how to validate inside method not as parameter @Valid
    * */
//
//    @PatchMapping("/discount")
//    public DiscountDto applyPatchToDiscount(@RequestBody DiscountDto discountDto) {
//        log.info("Attempt to change discount: " + discountDto);
//        DiscountDto discountFromDb = discountService.getDiscount();
//        if (discountDto.getStep() == null) {
//            discountDto.setStep(discountFromDb.getStep());
//        }
//        if (discountDto.getMax() == null) {
//            discountDto.setMax(discountFromDb.getMax());
//        }
//        discountDto.setId(discountFromDb.getId());
////        Set<ConstraintViolation<DiscountDto>> errors =
////                Validation.buildDefaultValidatorFactory().getValidator().validate(discountDto);
////
//        return discountService.updateDiscount(discountDto);
//    }



}
