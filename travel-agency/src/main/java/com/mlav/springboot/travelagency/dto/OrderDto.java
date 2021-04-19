package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.model.Status;
import com.mlav.springboot.travelagency.validation.order.OrderBasicInfo;
import com.mlav.springboot.travelagency.validation.order.OrderCustomAnnotations;
import com.mlav.springboot.travelagency.validation.order.OrderDefaultAnnotations;
import com.mlav.springboot.travelagency.validation.annotations.TourExistsConstraint;
import com.mlav.springboot.travelagency.validation.annotations.UserExistsConstraint;
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
    @NotNull(message = "Tour id is a mandatory field", groups = {OrderBasicInfo.class, OrderDefaultAnnotations.class})
    @TourExistsConstraint(groups = {OrderBasicInfo.class, OrderCustomAnnotations.class})
    private Long tourId;
    @NotNull(message = "User login is a mandatory field",
            groups = {OrderBasicInfo.class, OrderDefaultAnnotations.class})
    @UserExistsConstraint(groups = {OrderBasicInfo.class, OrderCustomAnnotations.class})
    private String userLogin;
    @NotNull(message = "Quantity of tours is a mandatory field",
            groups = {OrderBasicInfo.class, OrderDefaultAnnotations.class})
    @Min(value = 1, message = "Quantity of tours can't be less than 1", groups = OrderBasicInfo.class)
    private Integer numberOfTours;
    @NotNull(message = "Price cannot be null")
    private BigDecimal actualPrice;
    @NotNull(message = "Status can't be null")
    private Status status;
    @NotNull(message = "Date and time of purchase cannot be null")
    private LocalDateTime dateTimeOfPurchase;
}
