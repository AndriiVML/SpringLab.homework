package com.mlav.springboot.travelagency.controller.assembler;

import com.mlav.springboot.travelagency.controller.OrderController;
import com.mlav.springboot.travelagency.controller.model.OrderModel;
import com.mlav.springboot.travelagency.dto.OrderDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembler extends RepresentationModelAssemblerSupport<OrderDto, OrderModel> {
    public OrderAssembler() {
        super(OrderController.class, OrderModel.class);
    }

    @Override
    public OrderModel toModel(OrderDto entity) {
        OrderModel orderModel = new OrderModel(entity);

        Link get = linkTo(methodOn(OrderController.class)
                .getOrder(entity.getId()))
                .withRel("get");
        Link getAll = linkTo(methodOn(OrderController.class)
                .getAllOrders())
                .withRel("getAll");
        Link order = linkTo(methodOn(OrderController.class)
                .orderTour(entity))
                .withRel("order");
        Link changeStatus = linkTo(methodOn(OrderController.class)
                .changeStatus(entity.getId(), entity.getStatus().getName()))
                .withRel("change-status");

        Link delete = linkTo(methodOn(OrderController.class)
                .deleteOrder(entity.getId()))
                .withRel("delete");

        orderModel.add(get, getAll, order, changeStatus, delete);
        return orderModel;
    }
}
