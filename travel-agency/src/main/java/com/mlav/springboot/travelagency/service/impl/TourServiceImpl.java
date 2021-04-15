package com.mlav.springboot.travelagency.service.impl;

import com.springboot.homework4.util.Util;
import com.springboot.homework4.dto.TourDto;
import com.springboot.homework4.dto.TourRegisterDto;
import com.springboot.homework4.model.entity.Tour;
import com.springboot.homework4.repository.TourRepository;
import com.springboot.homework4.service.TourService;
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
                .tourType(tour.getTourType())
                .hotelType(tour.getHotelType())
                .numberOfTours(tour.getNumberOfTours())
                .numberOfParticipants(tour.getNumberOfParticipants())
                .isHot(tour.isHot())
                .build();
    }

    private Tour mapTourDtoToTour(TourDto tourDto) {
        Tour tour = Tour.builder()
                .tourName(tourDto.getTourName())
                .price(tourDto.getPrice())
                .tourType(tourDto.getTourType())
                .hotelType(tourDto.getHotelType())
                .numberOfTours(tourDto.getNumberOfTours())
                .numberOfParticipants(tourDto.getNumberOfParticipants())
                .isHot(tourDto.isHot())
                .build();
        tour.setId(tourDto.getId());
        return tour;
    }

    private TourRegisterDto mapTourToTourRegisterDto(Tour tour) {
        return TourRegisterDto.builder()
                .id(tour.getId())
                .tourName(tour.getTourName())
                .price(tour.getPrice())
                .tourType(tour.getTourType())
                .hotelType(tour.getHotelType())
                .numberOfTours(tour.getNumberOfTours())
                .numberOfParticipants(tour.getNumberOfParticipants())
                .build();
    }

    private Tour mapTourRegisterDtoToTour(TourRegisterDto tourRegisterDto) {
        Tour tour = Tour.builder()
                .tourName(tourRegisterDto.getTourName())
                .price(tourRegisterDto.getPrice())
                .tourType(tourRegisterDto.getTourType())
                .hotelType(tourRegisterDto.getHotelType())
                .numberOfTours(tourRegisterDto.getNumberOfTours())
                .numberOfParticipants(tourRegisterDto.getNumberOfParticipants())
                .build();
        tour.setId(Util.generateUniqueId());
        return tour;
    }


    @Override
    public TourDto getTour(long id) {
        log.info("Attempt to get tour with id=" + id);
        Tour tour = tourRepository.getTour(id);
        return mapTourToTourDto(tour);
    }

    @Override
    public List<TourDto> getAllTours() {
        log.info("Attempt to get all tours");
        List<Tour> tours = tourRepository.getAllTours();
        List<TourDto> tourDtos = new ArrayList<>();
        for (Tour t : tours) {
            tourDtos.add(mapTourToTourDto(t));
        }
        return tourDtos;
    }

    @Override
    public TourRegisterDto createTour(TourRegisterDto tourRegisterDto) {
        log.info("Attempt to create tour: " + tourRegisterDto);
        Tour tour = mapTourRegisterDtoToTour(tourRegisterDto);
        tour = tourRepository.createTour(tour);
        return mapTourToTourRegisterDto(tour);
    }

    @Override
    public void deleteTour(long id) {
        log.info("Attempt to delete tour with id=" + id);
        tourRepository.deleteTour(id);

    }

    @Override
    public TourDto updateTour(long id, TourDto tourDto) {
        log.info(String.format("Attempt to update tour with id=%d possibleUpdate: %s", id, tourDto));
        Tour tour = mapTourDtoToTour(tourDto);
        tour = tourRepository.updateTour(id, tour);
        return mapTourToTourDto(tour);
    }


}
