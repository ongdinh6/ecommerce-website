package vn.omdinh.demo.services;

import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.models.requests.OrderFilter;
import vn.omdinh.demo.models.requests.OrderRequest;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;

import java.util.Collection;

public interface OrderService {
    PaginatedResultResponse<Collection<OrderDTO>> selectAllOrders(OrderFilter orderFilter);
    OrderDTO createNewOrder(OrderRequest orderRequest);
}
