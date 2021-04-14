package com.springboot.homework4.controller;

import com.springboot.homework4.dto.OrderDto;
import com.springboot.homework4.dto.TourPurchaseDto;
import com.springboot.homework4.model.Status;
import com.springboot.homework4.service.OrderService;
import com.springboot.homework4.validation.order.OrderBasicInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        log.info("Attempt to get all orders");
        return orderService.getAllOrders();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public OrderDto getOrder(@PathVariable long id) {
        log.info("Attempt to get order with id=" + id);
        return orderService.getOrder(id);
    }

    //test in postman
    /*
    {
        "tourId":id,
        "userLogin":"newLogin",
        "numberOfTours":1
    }
    */


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderDto orderTour(@Validated(OrderBasicInfo.class) @RequestBody OrderDto orderDto) {
        log.info(
                String.format("Attempt to make order: userLogin=%s, tourId=%d, quantity=%d",
                        orderDto.getUserLogin(), orderDto.getTourId(), orderDto.getNumberOfTours()));
        return orderService.orderTour(orderDto);
    }

    @PatchMapping(value = "/{id}/change-status")
    public OrderDto changeStatus(@PathVariable long id, @RequestParam String status) {
        Status st = Status.valueOf(status);
        OrderDto orderDto = orderService.getOrder(id);
        log.info("Attempt to change status of order");
        orderDto = orderService.changeStatus(st, orderDto);
        return orderDto;
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        log.info("Attempt to delete order with id=" + id);
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


}


