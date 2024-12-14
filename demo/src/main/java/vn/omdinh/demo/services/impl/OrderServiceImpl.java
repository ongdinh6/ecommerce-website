package vn.omdinh.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.dtos.OrderProductDTO;
import vn.omdinh.demo.models.OrderStatus;
import vn.omdinh.demo.models.requests.OrderFilter;
import vn.omdinh.demo.models.requests.OrderRequest;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;
import vn.omdinh.demo.models.responses.ProductItemResponse;
import vn.omdinh.demo.repositories.OrderRepository;
import vn.omdinh.demo.services.OrderService;
import vn.omdinh.demo.services.ProductService;

import java.util.Collection;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Autowired
     public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
         this.orderRepository = orderRepository;
         this.productService = productService;
     }

    @Override
    public PaginatedResultResponse<Collection<OrderDTO>> selectAllOrders(OrderFilter orderFilter) {
        var data = this.orderRepository
            .selectAllOrders(orderFilter)
            .stream()
            .map(order -> order.into(OrderDTO.class))
            .toList();

        var total = this.orderRepository.countAllOrders(orderFilter);

        return new PaginatedResultResponse<>(
            total,
            orderFilter.getLimit(),
            total / orderFilter.getLimit(),
            orderFilter.getLimit(),
            orderFilter,
            data
        );
    }

    @Override
    public OrderDTO createNewOrder(OrderRequest orderRequest) {
        var productItemResponses = this.productService.calculateCost(orderRequest.getItems());

        var orderDTO = new OrderDTO();
        orderDTO.setReceiver(orderRequest.getReceiver());
        orderDTO.setTotalCost(productItemResponses.stream().mapToDouble(ProductItemResponse::finalCost).sum());
        orderDTO.setTotalItems(orderRequest.getItems().size());
        orderDTO.setAddressDelivery(orderRequest.getAddressDelivery());
        orderDTO.setPhoneNumber(orderRequest.getPhoneNumber());
        orderDTO.setStatus(OrderStatus.PENDING.name());

        // save into orders
        var newRecord = this.orderRepository.newRecord(orderDTO);

        var newOrder = newRecord.into(OrderDTO.class);
        this.orderRepository.store(newOrder);

        // save into order_product
        List<OrderProductDTO> orderProductDTOS = productItemResponses.stream().map(product -> new OrderProductDTO(
            product.id(),
            newOrder.getId(),
            product.amount()
        )).toList();

        this.orderRepository.storeOrderProduct(orderProductDTOS);

        return orderDTO;
    }
}
