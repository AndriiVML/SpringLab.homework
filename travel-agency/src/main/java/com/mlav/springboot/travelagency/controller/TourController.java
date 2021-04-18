package com.mlav.springboot.travelagency.controller;


import com.mlav.springboot.travelagency.dto.TourDto;
import com.mlav.springboot.travelagency.service.TourService;
import com.mlav.springboot.travelagency.validation.tour.TourPatchUpdate;
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
@RequestMapping("/tours")
@RequiredArgsConstructor
@Slf4j
public class TourController {
    private final TourService tourService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TourDto> getAllTours() {
        log.info("Attempt to get all tours");
        return tourService.getAllTours();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public TourDto getTour(@PathVariable long id) {
        log.info("Attempt to get tour with id=" + id);
        return tourService.getTour(id);
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TourDto createTour(@Valid @RequestBody TourDto tourDto) {
        log.info("Attempt to create tour: " + tourDto);
        return tourService.createTour(tourDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public TourDto updateTour(@PathVariable long id, @Validated(TourPutUpdate.class) @RequestBody TourDto tourDto) {
        log.info(String.format("Attempt to update tour with id=%d possibleUpdate: %s", id, tourDto));
        return tourService.updateTour(id, tourDto);
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


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable long id) {
        log.info("Attempt to delete tour with id=" + id);
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }

}
