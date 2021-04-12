package com.springboot.homework4.repository;

import com.springboot.homework4.model.entity.Tour;

import java.util.List;

public interface TourRepository {
    Tour getTour(long id);

    List<Tour> getAllTours();

    Tour createTour(Tour tour);

    Tour updateTour(long id, Tour tour);

    void deleteTour(long id);

}
