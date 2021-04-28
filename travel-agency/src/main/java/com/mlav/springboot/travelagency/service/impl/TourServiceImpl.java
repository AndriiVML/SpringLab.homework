package com.mlav.springboot.travelagency.service.impl;

import com.mlav.springboot.travelagency.exception.TourNotFoundException;
import com.mlav.springboot.travelagency.model.HotelType;
import com.mlav.springboot.travelagency.model.TourType;
import com.mlav.springboot.travelagency.util.Util;
import com.mlav.springboot.travelagency.dto.TourDto;
import com.mlav.springboot.travelagency.model.entity.Tour;
import com.mlav.springboot.travelagency.repository.TourRepository;
import com.mlav.springboot.travelagency.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;

    private TourDto mapTourToTourDto(Tour tour) {
        return TourDto.builder()
                .id(tour.getId())
                .tourName(tour.getTourName())
                .price(tour.getPrice())
                .tourType(TourType.getTourType(tour))
                .hotelType(HotelType.getHotelType(tour))
                .numberOfTours(tour.getNumberOfTours())
                .numberOfParticipants(tour.getNumberOfParticipants())
                .isHot(tour.getIsHot())
                .build();
    }

    private Tour mapTourDtoToTour(TourDto tourDto) {
        Tour tour = Tour.builder()
                .tourName(tourDto.getTourName())
                .price(tourDto.getPrice())
                .tourTypeId(tourDto.getTourType().ordinal())
                .hotelTypeId(tourDto.getHotelType().ordinal())
                .numberOfTours(tourDto.getNumberOfTours())
                .numberOfParticipants(tourDto.getNumberOfParticipants())
                .isHot(tourDto.getIsHot())
                .build();
        tour.setId(tourDto.getId());
        return tour;
    }



    @Override
    public TourDto getTour(long id) {
        log.info("Attempt to get tour with id=" + id);
        Tour tour = tourRepository.findByIdAndIsDeletedFalse(id).orElseThrow(TourNotFoundException::new);
        log.info("Tour: {}", tour);
        return mapTourToTourDto(tour);
    }

    @Override
    public List<TourDto> getAllTours() {
        log.info("Attempt to get all tours");
        List<Tour> tours = tourRepository.findAllByIsDeletedFalse();
        List<TourDto> tourDtos = new ArrayList<>();
        for (Tour t : tours) {
            tourDtos.add(mapTourToTourDto(t));
        }
        log.info("All tours: {}", tours);
        return tourDtos;
    }

    @Override
    public TourDto createTour(TourDto tourDto) {
        log.info("Attempt to create tour: " + tourDto);
        Tour tour = mapTourDtoToTour(tourDto);
        tour.setIsDeleted(false);
        tour = tourRepository.save(tour);
        log.info("New tour: {}", tour);
        return mapTourToTourDto(tour);
    }

    @Override
    public void deleteTour(long id) {
        log.info("Attempt to delete tour with id=" + id);
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException("Cannot delete a non-existent tour"));
        tour.setIsDeleted(true);
        tourRepository.save(tour);
        log.info("Tour is deleted");
    }

    @Override
    public TourDto updateTour(long id, TourDto tourDto) {
        log.info(String.format("Attempt to update tour with id=%d possibleUpdate: %s", id, tourDto));
        Tour tour = mapTourDtoToTour(tourDto);
        Tour tourFromDb = tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException("Cannot update tour. Tour does not exist"));
        tour.setId(tourFromDb.getId());
        tour.setIsDeleted(tourFromDb.getIsDeleted());
        tour = tourRepository.save(tour);
        log.info("Tour is updated: {}", tour);
        return mapTourToTourDto(tour);
    }


}
