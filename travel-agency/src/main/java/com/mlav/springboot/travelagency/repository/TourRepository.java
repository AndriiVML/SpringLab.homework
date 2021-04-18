package com.mlav.springboot.travelagency.repository;

import com.mlav.springboot.travelagency.model.entity.Tour;

import java.util.List;

public interface TourRepository {
    int getCountOfDisabledTours();

    int getCountOfActiveTours();

    int getCount();

    Tour getTour(long id);

    List<Tour> getAllTours();

    Tour createTour(Tour tour);

    Tour updateTour(long id, Tour tour);

    void deleteTour(long id);

}
