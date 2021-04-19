package com.mlav.springboot.travelagency.controller.model;


import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mlav.springboot.travelagency.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class OrderModel extends RepresentationModel<OrderModel> {
    @JsonUnwrapped
    private OrderDto orderDto;
}
