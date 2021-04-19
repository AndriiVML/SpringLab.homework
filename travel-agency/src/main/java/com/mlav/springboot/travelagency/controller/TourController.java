package com.mlav.springboot.travelagency.controller;


import com.mlav.springboot.travelagency.api.TourApi;
import com.mlav.springboot.travelagency.controller.assembler.TourAssembler;
import com.mlav.springboot.travelagency.controller.model.TourModel;
import com.mlav.springboot.travelagency.dto.TourDto;
import com.mlav.springboot.travelagency.service.TourService;
import com.mlav.springboot.travelagency.validation.tour.TourPutUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController

@RequiredArgsConstructor
@Slf4j
public class TourController implements TourApi {
    private final TourService tourService;
    private final TourAssembler tourAssembler;

    private List<TourModel> mapListTourDtoToListTourModel(List<TourDto> tourDtos) {
        List<TourModel> tourModels = new ArrayList<>(tourDtos.size());
        for (TourDto item : tourDtos) {
            tourModels.add(tourAssembler.toModel(item));
        }
        return tourModels;
    }


    @Override
    public List<TourModel> getAllTours() {
        log.info("Attempt to get all tours");
        List<TourDto> allTours = tourService.getAllTours();
        return mapListTourDtoToListTourModel(allTours);
    }

    @Override
    public TourModel getTour(long id) {
        log.info("Attempt to get tour with id=" + id);
        TourDto entity = tourService.getTour(id);
        return tourAssembler.toModel(entity);
    }
    //test in postman
    /*
    {
        "tourName":"tourName",
        "price":"399.99",
        "numberOfTours":"100",
        "numberOfParticipants":"1",
        "hotelType":"THREE_STAR",
        "tourType":"EXCURSION",
        "isHot":"false"
    }
    */

    @Override
    public TourModel createTour(TourDto tourDto) {
        log.info("Attempt to create tour: " + tourDto);
        TourDto entity = tourService.createTour(tourDto);
        return tourAssembler.toModel(entity);
    }

    @Override
    public TourModel updateTour(long id, TourDto tourDto) {
        log.info(String.format("Attempt to update tour with id=%d possibleUpdate: %s", id, tourDto));
        TourDto entity = tourService.updateTour(id, tourDto);
        return tourAssembler.toModel(entity);
    }


    /*
    * This method tests only with string sets
    * example correct:
        {
            "tourName": "tourNam2e",
            "price": "4399.99",
            "numberOfTours": "1040",
            "numberOfParticipants": "1",
            "hotelType": "FOUR_STAR",
            "tourType": "VACATION",
            "hot": false
        }
    * wrong:
        {
            "tourName": "tourNam2e",
            "price": 4399.99,//err
            "numberOfTours": 1040,//err
            "numberOfParticipants": 1,//err
            "hotelType": "FOUR_STAR",
            "tourType": "VACATION",
            "hot": false//err
        }
    * */


    /*
     * Do not know how to valid after initialization all fields inside method
     * */
//
//    @PatchMapping(value = "/{id}")
//    public TourDto applyPatchToTour(@PathVariable long id,
//                                    @Validated(TourPatchUpdate.class) @RequestBody TourDto tourDto) {
//        log.info(String.format("Attempt to update tour with id=%d possibleUpdate: %s", id, tourDto));
//        TourDto tourFromDb = tourService.getTour(id);
//        if (tourDto.getTourName() == null) {
//            tourDto.setTourName(tourFromDb.getTourName());
//        }
//        if (tourDto.getTourType() == null) {
//            tourDto.setTourType(tourFromDb.getTourType());
//        }
//        if (tourDto.getHotelType() == null) {
//            tourDto.setHotelType(tourFromDb.getHotelType());
//        }
//        if (tourDto.getNumberOfParticipants() == null) {
//            tourDto.setNumberOfParticipants(tourFromDb.getNumberOfParticipants());
//        }
//        if(tourDto.getNumberOfTours()==null){
//            tourDto.setNumberOfTours(tourFromDb.getNumberOfTours());
//        }
//        if (tourDto.getPrice() == null) {
//            tourDto.setPrice(tourFromDb.getPrice());
//        }
//        if (tourDto.getIsHot() == null) {
//            tourDto.setIsHot(tourFromDb.getIsHot());
//        }
//        return tourService.updateTour(id, tourDto);
//    }

    //maybe instead of patch patch only on hot status of tour like in UserController


    @Override
    public ResponseEntity<Void> deleteTour(long id) {
        log.info("Attempt to delete tour with id=" + id);
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }

}
