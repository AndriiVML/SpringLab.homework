package com.mlav.springboot.travelagency.service;

import com.mlav.springboot.travelagency.dto.TourDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TourService {
    TourDto getTour(long id);

    List<TourDto> getAllTours(List<Sort.Order> orders);

    List<TourDto> findPaginated(int pageNumber, int pageSize, List<Sort.Order> orders);

    TourDto createTour(TourDto tourDto);

    void deleteTour(long id);

    TourDto updateTour(long id, TourDto tourDto);

}
