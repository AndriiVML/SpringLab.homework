package com.mlav.springboot.travelagency.api;

import com.mlav.springboot.travelagency.controller.model.OrderModel;
import com.mlav.springboot.travelagency.dto.OrderDto;
import com.mlav.springboot.travelagency.validation.order.OrderBasicInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Order management API")
@RequestMapping("/api/v1/tours/orders")
public interface OrderApi {
    @ApiOperation("Get all orders from database")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<OrderModel> getAllOrders();

    @ApiOperation("Get page of orders from database")
    @GetMapping("/{pageNumber}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    List<OrderModel> getPaginatedAndSorted(@PathVariable int pageNumber, @PathVariable int pageSize,
                                           @RequestParam String[] sort);

    @ApiOperation("Get all orders for user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{login}")
    List<OrderModel> getUserOrders(@PathVariable String login);

    @ApiOperation("Get order from database")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    OrderModel getOrder(@PathVariable long id);

    @ApiOperation("Make order and add it to the database")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    OrderModel orderTour(@Validated(OrderBasicInfo.class) @RequestBody OrderDto orderDto);

    @ApiOperation("Change status of order(approve or cancel) in database")
    @PatchMapping(value = "/{id}/change-status")
    OrderModel changeStatus(@PathVariable long id, @RequestParam String status);

    @ApiOperation("Delete order from database")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable long id);
}
