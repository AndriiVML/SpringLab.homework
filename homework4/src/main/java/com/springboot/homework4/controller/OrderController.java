package com.springboot.homework4.controller;

import com.springboot.homework4.model.Status;
import com.springboot.homework4.model.entity.TourPurchase;
import com.springboot.homework4.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tours/orders")
@Log4j2
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TourPurchase> getAllOrder() {
        return orderService.getAllOrders();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public TourPurchase getTour(@PathVariable long id) {
        return orderService.getOrder(id);
    }

    //test in postman
    /*
    {
        "tourId":"id",
        "userLogin":"newLogin",
        "quantity":"10"
    }
    */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TourPurchase orderTour(@RequestBody Map<String, String> json) {
        long tourId = Long.parseLong(json.get("tourId"));
        String userLogin = json.get("userLogin");
        int quantity = Integer.parseInt(json.get("quantity"));

        return orderService.orderTour(tourId, userLogin, quantity);
    }

    @PatchMapping(value = "/{id}/status")
    public TourPurchase changeStatus(@PathVariable long id, @RequestParam String status) {
        Status st = Status.valueOf(status);
        TourPurchase tourPurchase = orderService.getOrder(id);
        tourPurchase = orderService.changeStatus(st, tourPurchase);
        return tourPurchase;
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


}


