package com.mlav.springboot.travelagency.model.entity;

import com.mlav.springboot.travelagency.model.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TourPurchase  {
    private Long id;
    private Tour tour;
    private User user;
    private BigDecimal actualPrice;
    private int numberOfTours;
    private Status status;
    private LocalDateTime dateTimeOfPurchase;

    //for future https://stackoverflow.com/questions/48526820/convert-a-locale-date-into-mysql-datetime-format
}
