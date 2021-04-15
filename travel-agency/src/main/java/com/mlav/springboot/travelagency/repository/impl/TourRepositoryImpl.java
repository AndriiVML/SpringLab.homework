package com.mlav.springboot.travelagency.repository.impl;

import com.springboot.homework4.model.entity.Tour;
import com.springboot.homework4.repository.TourRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TourRepositoryImpl implements TourRepository {
    private List<Tour> list = new ArrayList<>();


    @Override
    public int getCountOfDisabledTours() {
        return list.size() - getCountOfActiveTours();
    }

    @Override
    public int getCountOfActiveTours() {
        return getAllTours().size();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Tour getTour(long id) {
        Tour tour = list.stream()
                .filter(t -> t.getId() == id)
                .filter(t -> !t.isDeleted())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tour is not found"));
        log.info("tour: " + tour);
        return tour;
    }

    @Override
    public List<Tour> getAllTours() {
        List<Tour> result = list.stream()
                .filter(tour -> !tour.isDeleted())
                .collect(Collectors.toList());
        log.info("all tours: " + result);
        return result;
    }

    @Override
    public Tour createTour(Tour tour) {
        //need to add some check
        list.add(tour);
        log.info("New tour:" + tour);
        return tour;
    }

    @Override
    public Tour updateTour(long id, Tour tour) {
        tour.setId(id);
        boolean isDeleted = list.removeIf(t -> t.getId() == id);
        if (isDeleted) {
            list.add(tour);

        } else {
            log.error("Cannot update tour. Tour does not exists: " + tour);
            throw new RuntimeException("Tour does not exist.");
        }
        log.info("Tour is updated: " + tour);
        return tour;
    }

    /*
     * Not deleting tours from list, because users may have orders with deleted tour(to prevent cascade deleting:
     * if tour's deleted => all orders with this tour will be deleted)
     * */
    @Override
    public void deleteTour(long id) {
        Tour tour = list.stream()
                .filter(t -> t.getId() == id && !t.isDeleted())
                .findFirst().orElseThrow(() -> new RuntimeException("Cannot delete a non-existent tour "));
        list.remove(tour);
        tour.setDeleted(true);
        list.add(tour);
        log.info("Tour is deleted");
    }


}
