package vn.omdinh.demo.repositories.impl;

import nu.studer.sample.tables.Orders;
import nu.studer.sample.tables.records.OrdersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.repositories.OrderRepository;

import java.util.Collection;

import static nu.studer.sample.tables.Orders.ORDERS;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    private final DSLContext dslContext;

    @Autowired
    public OrderRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Collection<OrdersRecord> selectAllOrders(PaginatedSearch paginatedSearch) {
        return this.dslContext
            .selectFrom(ORDERS)
            .where(ORDERS.ID.eq(paginatedSearch.getLastItemId()))
            .and(ORDERS.RECEIVER.likeIgnoreCase("%" + paginatedSearch.getQuery() + "%"))
            .orderBy(ORDERS.ID)
            .seek(paginatedSearch.getLastItemId())
            .limit(paginatedSearch.getLimit())
            .stream()
            .toList();
    }

    @Override
    public int countAllOrders(PaginatedSearch paginatedSearch) {
        return this.dslContext.fetchCount(
            this.dslContext
                .selectFrom(ORDERS)
                .where(ORDERS.ID.eq(paginatedSearch.getLastItemId()))
                .and(ORDERS.RECEIVER.likeIgnoreCase("%" + paginatedSearch.getQuery() + "%"))
        );
    }
}
