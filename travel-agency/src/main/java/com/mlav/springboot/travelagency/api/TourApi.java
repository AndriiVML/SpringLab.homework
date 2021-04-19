package com.mlav.springboot.travelagency.api;

import com.mlav.springboot.travelagency.controller.model.TourModel;
import com.mlav.springboot.travelagency.dto.TourDto;
import com.mlav.springboot.travelagency.validation.tour.TourPutUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Api(tags = "Tour management API")
@RequestMapping("/api/v1/tours")
public interface TourApi {
    @ApiOperation("Get all tours from database")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TourModel> getAllTours();

    @ApiOperation("Get tour from database")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    TourModel getTour(@PathVariable long id);

    @ApiOperation("Create tour and add it to the database")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    TourModel createTour(@Valid @RequestBody TourDto tourDto);

    @ApiOperation("Update tour in database(need to change at least one field(except \"numberOfTours\") to update")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    TourModel updateTour(@PathVariable long id, @Validated(TourPutUpdate.class) @RequestBody TourDto tourDto);

    @ApiOperation("Delete tour from database")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteTour(@PathVariable long id);
}