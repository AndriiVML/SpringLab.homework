package com.mlav.springboot.travelagency.service;

import com.mlav.springboot.travelagency.dto.TourDto;

import java.util.List;

public interface TourService {
    TourDto getTour(long id);

    List<TourDto> getAllTours();

    List<TourDto> findPaginated(int pageNumber, int pageSize);

    TourDto createTour(TourDto tourDto);

    void deleteTour(long id);

    TourDto updateTour(long id, TourDto tourDto);

}
