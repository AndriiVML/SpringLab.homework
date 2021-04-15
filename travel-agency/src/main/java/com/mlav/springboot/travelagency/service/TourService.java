package com.mlav.springboot.travelagency.service;

import com.springboot.homework4.dto.TourDto;
import com.springboot.homework4.dto.TourRegisterDto;

import java.util.List;

public interface TourService {
    TourDto getTour(long id);

    List<TourDto> getAllTours();

    TourRegisterDto createTour(TourRegisterDto tourRegisterDto);

    void deleteTour(long id);

    TourDto updateTour(long id, TourDto tourDto);

}
