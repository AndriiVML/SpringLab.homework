package com.springboot.homework4.contributor;

import com.springboot.homework4.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TotalToursInfoContributor implements InfoContributor {
    private final TourRepository tourRepository;
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> tourDetails = new HashMap<>();
        tourDetails.put("count", tourRepository.getCount());
        tourDetails.put("active tours",tourRepository.getCountOfActiveTours());
        tourDetails.put("disabled tours",tourRepository.getCountOfDisabledTours());
        builder.withDetail("tours", tourDetails);
    }
}
