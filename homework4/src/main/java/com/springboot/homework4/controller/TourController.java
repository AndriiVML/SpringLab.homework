package com.springboot.homework4.controller;


import com.springboot.homework4.dto.TourDto;
import com.springboot.homework4.dto.TourRegisterDto;
import com.springboot.homework4.model.HotelType;
import com.springboot.homework4.model.TourType;
import com.springboot.homework4.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tours")
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TourDto> getAllTours(){
        return tourService.getAllTours();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public TourDto getTour(@PathVariable long id) {
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
        "tourType":"EXCURSION"
    }
    */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TourRegisterDto createTour(@RequestBody TourRegisterDto TourRegisterDto) {
        return tourService.createTour(TourRegisterDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public TourDto updateTour(@PathVariable long id, @RequestBody TourDto TourDto) {
        return tourService.updateTour(id, TourDto);
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

    @PatchMapping(value = "/{id}")
    public TourDto applyPatchToTour(@PathVariable long id, @RequestBody Map<Object, Object> fields) {
        final TourDto tourDto = tourService.getTour(id);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(TourDto.class, (String) k);
            field.setAccessible(true);
            if (k.equals("numberOfTours")) {
                ReflectionUtils.setField(field, tourDto, Integer.parseInt((String) v));
                return;//only skips this iteration
            }
            if (k.equals("numberOfParticipants")) {
                ReflectionUtils.setField(field, tourDto, Integer.parseInt((String) v));
                return;//only skips this iteration
            }
            if(k.equals("price")){
                ReflectionUtils.setField(field, tourDto, new BigDecimal((String) v));
                return;//only skips this iteration
            }
            if (k.equals("isHot")) {
                ReflectionUtils.setField(field, tourDto, Boolean.parseBoolean((String) v));
                return;//only skips this iteration
            }
            if(k.equals("hotelType")){
                ReflectionUtils.setField(field, tourDto, HotelType.valueOf((String) v));
                return;//only skips this iteration
            }
            if(k.equals("tourType")){
                ReflectionUtils.setField(field, tourDto, TourType.valueOf((String) v));
                return;//only skips this iteration
            }

            ReflectionUtils.setField(field, tourDto, v);
        });
        return tourService.updateTour(id, tourDto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable long id) {
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }

}
