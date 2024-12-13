package vn.omdinh.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;
import vn.omdinh.demo.services.OrderService;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    ResponseEntity<PaginatedResultResponse<Collection<OrderDTO>>> selectAllOrders(@RequestParam PaginatedSearch paginatedSearch) {
        return ResponseEntity.ok().body(this.orderService.selectAllOrders(paginatedSearch));
    }

}
