package vn.omdinh.demo.services;

import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;

import java.util.Collection;

public interface OrderService {
    PaginatedResultResponse<Collection<OrderDTO>> selectAllOrders(PaginatedSearch paginatedSearch);
}
