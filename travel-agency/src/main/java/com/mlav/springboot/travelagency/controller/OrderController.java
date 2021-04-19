package com.mlav.springboot.travelagency.controller;

import com.mlav.springboot.travelagency.controller.assembler.OrderAssembler;
import com.mlav.springboot.travelagency.controller.model.OrderModel;
import com.mlav.springboot.travelagency.dto.OrderDto;
import com.mlav.springboot.travelagency.model.Status;
import com.mlav.springboot.travelagency.service.OrderService;
import com.mlav.springboot.travelagency.validation.order.OrderBasicInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tours/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderAssembler orderAssembler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderModel> getAllOrders() {
        log.info("Attempt to get all orders");
        List<OrderDto> allOrders = orderService.getAllOrders();
        return mapListOrderDtoToListOrderModel(allOrders);
    }


    private List<OrderModel> mapListOrderDtoToListOrderModel(List<OrderDto> orderDtos) {
        List<OrderModel> orderModels = new ArrayList<>(orderDtos.size());
        for (OrderDto item : orderDtos) {
            orderModels.add(orderAssembler.toModel(item));
        }

        return orderModels;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public OrderModel getOrder(@PathVariable long id) {
        log.info("Attempt to get order with id=" + id);
        OrderDto entity = orderService.getOrder(id);
        return orderAssembler.toModel(entity);
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
    public OrderModel orderTour(@Validated(OrderBasicInfo.class) @RequestBody OrderDto orderDto) {
        log.info(
                String.format("Attempt to make order: userLogin=%s, tourId=%d, quantity=%d",
                        orderDto.getUserLogin(), orderDto.getTourId(), orderDto.getNumberOfTours()));
        OrderDto entity = orderService.orderTour(orderDto);
        return orderAssembler.toModel(entity);
    }

    @PatchMapping(value = "/{id}/change-status")
    public OrderModel changeStatus(@PathVariable long id, @RequestParam String status) {
        Status st = Status.valueOf(status);
        OrderDto orderDto = orderService.getOrder(id);
        log.info("Attempt to change status of order");
        orderDto = orderService.changeStatus(st, orderDto);

        return orderAssembler.toModel(orderDto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        log.info("Attempt to delete order with id=" + id);
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


}


