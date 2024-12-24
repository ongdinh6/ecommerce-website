package vn.omdinh.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.dtos.OrderProductDTO;
import vn.omdinh.demo.models.OrderStatus;
import vn.omdinh.demo.models.requests.OrderFilter;
import vn.omdinh.demo.models.requests.OrderRequest;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;
import vn.omdinh.demo.models.responses.ProductItemResponse;
import vn.omdinh.demo.repositories.OrderRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Autowired
     public OrderService(OrderRepository orderRepository, ProductService productService) {
         this.orderRepository = orderRepository;
         this.productService = productService;
     }

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

    public OrderDTO createNewOrder(OrderRequest orderRequest) {
        var productItemResponses = this.productService.calculateCost(orderRequest.getItems());

        var orderDTO = new OrderDTO();
        orderDTO.setReceiver(orderRequest.getReceiver());
        orderDTO.setTotalCost(productItemResponses.stream().mapToDouble(ProductItemResponse::finalCost).sum());
        orderDTO.setTotalItems(orderRequest.getItems().size());
        orderDTO.setAddressDelivery(orderRequest.getAddressDelivery());
        orderDTO.setPhoneNumber(orderRequest.getPhoneNumber());
        orderDTO.setStatus(OrderStatus.PENDING.name());
        orderDTO.setCreatedAt(new Date());

        // save into orders
        var newOrder = this.orderRepository.newRecord(orderDTO).into(OrderDTO.class);

        List<OrderProductDTO> orderProductDTOS = productItemResponses.stream().map(product -> new OrderProductDTO(
            newOrder.getId(),
            product.id(),
            product.amount()
        )).toList();

        this.orderRepository.store(newOrder, orderProductDTOS);

        return newOrder;
    }
}
