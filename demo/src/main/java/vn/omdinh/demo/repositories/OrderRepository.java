package vn.omdinh.demo.repositories;

import vn.omdinh.tables.records.OrdersRecord;
import org.springframework.stereotype.Repository;
import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.dtos.OrderProductDTO;
import vn.omdinh.demo.models.requests.OrderFilter;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository {
    Collection<OrdersRecord> selectAllOrders(OrderFilter orderFilter);
    int countAllOrders(OrderFilter orderFilter);
    OrdersRecord newRecord(OrderDTO orderDTO);
    void store(OrderDTO orderDTO, List<OrderProductDTO> orderedItems);
}
