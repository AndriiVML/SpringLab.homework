package com.springboot.homework4.dto;

import com.springboot.homework4.model.Status;
import com.springboot.homework4.validation.order.OrderBasicInfo;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    private long id;
    @Min(value = 1,message = "Tour id is a mandatory field",groups = OrderBasicInfo.class)
    private long tourId;
    @NotNull(message = "User login is a mandatory field",groups = OrderBasicInfo.class)
    private String userLogin;
    @Min(value = 1, message = "Quantity of tours can't be less than 1",groups = OrderBasicInfo.class)
    private int numberOfTours;
    @NotNull(message = "Price cannot be null")
    private BigDecimal actualPrice;
    @NotNull(message = "Status can't be null")
    private Status status;
    @NotNull(message = "Date and time of purchase cannot be null")
    private LocalDateTime dateTimeOfPurchase;
}
