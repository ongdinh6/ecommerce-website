package vn.omdinh.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.exceptions.ExceptionResponse;
import vn.omdinh.demo.models.requests.OrderFilter;
import vn.omdinh.demo.models.requests.OrderRequest;
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
    @Operation(summary = "Gets all orders")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500",
                description = "Internal server error",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
        }
    )
    ResponseEntity<PaginatedResultResponse<Collection<OrderDTO>>> selectAllOrders(OrderFilter orderFilter) {
        return ResponseEntity.ok().body(this.orderService.selectAllOrders(orderFilter));
    }

    @PostMapping("/new")
    @Operation(summary = "Create new order")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201", description = "Create success"),
            @ApiResponse(responseCode = "400",
                description = "Invalid request body format",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
            @ApiResponse(responseCode = "500",
                description = "Internal server error",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
        }
    )
    ResponseEntity<OrderDTO> createNewOrder(@Valid @RequestBody OrderRequest orderRequest) {
        var newOrder = this.orderService.createNewOrder(orderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
}
