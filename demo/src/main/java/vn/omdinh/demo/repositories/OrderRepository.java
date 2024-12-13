package vn.omdinh.demo.repositories;

import nu.studer.sample.tables.records.OrdersRecord;
import org.springframework.stereotype.Repository;
import vn.omdinh.demo.models.requests.PaginatedSearch;

import java.util.Collection;

@Repository
public interface OrderRepository {
    Collection<OrdersRecord> selectAllOrders(PaginatedSearch paginatedSearch);
    int countAllOrders(PaginatedSearch paginatedSearch);
}
