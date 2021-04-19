package com.mlav.springboot.travelagency.controller;

import com.mlav.springboot.travelagency.api.DiscountApi;
import com.mlav.springboot.travelagency.controller.assembler.DiscountAssembler;
import com.mlav.springboot.travelagency.controller.model.DiscountModel;
import com.mlav.springboot.travelagency.dto.DiscountDto;
import com.mlav.springboot.travelagency.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Slf4j
@RequiredArgsConstructor
public class DiscountController implements DiscountApi {
    private final DiscountService discountService;
    private final DiscountAssembler discountAssembler;

    @Override
    public DiscountModel getDiscount() {
        log.info("Attempt to get general discount");
        DiscountDto entity = discountService.getDiscount();
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
