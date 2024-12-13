package vn.omdinh.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;
import vn.omdinh.demo.repositories.OrderRepository;
import vn.omdinh.demo.services.OrderService;

import java.util.Collection;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
     public OrderServiceImpl(OrderRepository orderRepository) {
         this.orderRepository = orderRepository;
     }

    @Override
    public PaginatedResultResponse<Collection<OrderDTO>> selectAllOrders(PaginatedSearch paginatedSearch) {
        var data = this.orderRepository
            .selectAllOrders(paginatedSearch)
            .stream()
            .map(order -> order.into(OrderDTO.class))
            .toList();

        var total = this.orderRepository.countAllOrders(paginatedSearch);

        return new PaginatedResultResponse<>(
            total,
            paginatedSearch.getLimit(),
            total / paginatedSearch.getLimit(),
            paginatedSearch.getLimit(),
            paginatedSearch,
            data
        );
    }
}
