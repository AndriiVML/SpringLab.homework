package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.model.Status;
import com.mlav.springboot.travelagency.model.entity.Tour;
import com.mlav.springboot.travelagency.model.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TourPurchaseDto {

    private long id;
    @NotNull(message = "Tour cannot be null")
    private Tour tour;
    @NotNull(message = "Tour cannot be null")
    private User user;
    @NotNull(message = "Price cannot be null")
    private BigDecimal actualPrice;
    @Min(value = 1, message = "Number of tours can't be less than 1")
    private int numberOfTours;
    @NotNull(message = "Status can't be null")
    private Status status;
    @NotNull(message = "Date and time of purchase cannot be null")
    private LocalDateTime dateTimeOfPurchase;
}
