package com.springboot.homework4.controller;


import com.springboot.homework4.dto.TourDto;
import com.springboot.homework4.dto.TourRegisterDto;
import com.springboot.homework4.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping(value = "/{id}")
    public TourDto changeTourBlockStatus(@PathVariable long id) {
        return tourService.changeTourHotStatus(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable long id) {
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }

}
