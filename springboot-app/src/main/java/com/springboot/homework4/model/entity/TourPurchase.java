package com.springboot.homework4.model.entity;

import com.springboot.homework4.model.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TourPurchase extends Entity {
    private Tour tour;
    private User user;
    private BigDecimal actualPrice;
    private int numberOfTours;
    private Status status;
    private LocalDateTime dateTimeOfPurchase;

    //for future https://stackoverflow.com/questions/48526820/convert-a-locale-date-into-mysql-datetime-format
}
