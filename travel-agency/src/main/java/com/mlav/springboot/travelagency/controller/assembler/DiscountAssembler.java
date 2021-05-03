package com.mlav.springboot.travelagency.controller.assembler;

import com.mlav.springboot.travelagency.controller.DiscountController;
import com.mlav.springboot.travelagency.controller.model.DiscountModel;
import com.mlav.springboot.travelagency.dto.DiscountDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class DiscountAssembler extends RepresentationModelAssemblerSupport<DiscountDto, DiscountModel> {
    public DiscountAssembler() {
        super(DiscountController.class, DiscountModel.class);
    }

    @Override
    public DiscountModel toModel(DiscountDto entity) {
        DiscountModel discountModel = new DiscountModel(entity);

        Link get = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DiscountController.class)
                .getDiscount())
                .withRel("get");

        Link updatePut = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DiscountController.class)
                .updateDiscount(entity))
                .withRel("put");
        Link updatePatch = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DiscountController.class)
                .applyPatchToDiscount(entity))
                .withRel("patch");
        discountModel.add(get, updatePut, updatePatch);
        return discountModel;
    }
}
