package com.mlav.springboot.travelagency.controller.assembler;

import com.mlav.springboot.travelagency.controller.TourController;
import com.mlav.springboot.travelagency.controller.model.TourModel;
import com.mlav.springboot.travelagency.dto.TourDto;
import com.mlav.springboot.travelagency.util.Util;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TourAssembler extends RepresentationModelAssemblerSupport<TourDto, TourModel> {

    public TourAssembler() {
        super(TourController.class, TourModel.class);
    }

    @Override
    public TourModel toModel(TourDto entity) {
        TourModel tourModel = new TourModel(entity);

        Link get = linkTo(methodOn(TourController.class)
                .getTour(entity.getId()))
                .withRel("get");
        Link getPaginatedAndSorted = linkTo(methodOn(TourController.class)
                .getPaginatedAndSorted(0, Util.PAGE_SIZE, new String[]{"isHot,desc"}))
                .withRel("getPaginatedAndSorted");
        Link getAll = linkTo(methodOn(TourController.class)
                .getAllTours(new String[]{"isHot,desc"}))
                .withRel("getAll");
        Link create = linkTo(methodOn(TourController.class)
                .createTour(entity))
                .withRel("create");
        Link updatePut = linkTo(methodOn(TourController.class)
                .updateTour(entity.getId(), entity))
                .withRel("put");
        Link updatePatch = linkTo(methodOn(TourController.class)
                .applyPatchToTour(entity.getId(), entity))
                .withRel("patch");
        Link delete = linkTo(methodOn(TourController.class)
                .deleteTour(entity.getId()))
                .withRel("delete");
        tourModel.add(get, getPaginatedAndSorted, getAll, create, updatePut, updatePatch, delete);
        return tourModel;
    }
}
