package com.mlav.springboot.travelagency.api;

import com.mlav.springboot.travelagency.controller.model.DiscountModel;
import com.mlav.springboot.travelagency.dto.DiscountDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Discount management API")
@RequestMapping("/api/v1/users")
public interface DiscountApi {
    @ApiOperation("Get discount(step and max discount) from database")
    @GetMapping("/discount")
    DiscountModel getDiscount();

    @ApiOperation("Update(put) discount in database")
    @PutMapping("/discount")
    DiscountModel updateDiscount(@Valid @RequestBody DiscountDto discountDto);


    @ApiOperation("Update(patch) discount int database")
    @PatchMapping("/discount")
    public DiscountDto applyPatchToDiscount(@RequestBody DiscountDto discountDto);

}
