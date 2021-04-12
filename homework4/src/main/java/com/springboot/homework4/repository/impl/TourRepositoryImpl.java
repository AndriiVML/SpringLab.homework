package com.springboot.homework4.repository.impl;

import com.springboot.homework4.model.entity.Tour;
import com.springboot.homework4.repository.TourRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class TourRepositoryImpl implements TourRepository {
    private List<Tour> list = new ArrayList<>();


    @Override
    public Tour getTour(long id) {
        return list.stream()
                .filter(t -> t.getId() == id)
                .filter(t -> !t.isDeleted())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found tour with id = " + id));
    }

    @Override
    public List<Tour> getAllTours() {
        List<Tour> result = list.stream()
                .filter(tour -> !tour.isDeleted())
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Tour createTour(Tour tour) {
        Tour tour1 = getTour(tour.getId());
        if (tour.equals(tour1)) {
            log.error("Unsuccessful attempt to create tour. Tour's already created: " + tour);
            throw new RuntimeException("Tour is already registered.");
        }
        list.add(tour);
        return tour;
    }

    @Override
    public Tour updateTour(long id, Tour tour) {
        tour.setId(id);
        boolean isDeleted = list.removeIf(t -> t.getId() == id);
        if (isDeleted) {
            list.add(tour);

        } else {
            log.error("Cannot update tour. Tour does not exists " + tour);
            throw new RuntimeException("Tour does not exist.");
        }
        return tour;
    }

    /*
     * Not deleting tours from list, because users may have orders with deleted tour(to prevent cascade deleting:
     * if tour's deleted => all orders with this tour will be deleted)
     * */
    @Override
    public void deleteTour(long id) {
        Tour tour = list.stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElseThrow(() -> new RuntimeException("Cannot delete a non-existent tour "));
        list.remove(tour);
        tour.setDeleted(true);
        list.add(tour);

    }

    @Override
    public Tour changeHotStatus(long id) {
        Tour tour = list.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found tour with id = " + id));
        boolean isHot = tour.isHot();
        tour.setHot(!isHot);
        return tour;
    }


}
