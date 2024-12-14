package vn.omdinh.demo.repositories.impl;

import nu.studer.sample.tables.records.OrdersRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.dtos.OrderProductDTO;
import vn.omdinh.demo.models.requests.OrderFilter;
import vn.omdinh.demo.repositories.OrderRepository;
import vn.omdinh.demo.utils.StringUtils;

import java.util.Collection;
import java.util.List;

import static nu.studer.sample.tables.Orders.ORDERS;
import static nu.studer.sample.tables.OrderProduct.ORDER_PRODUCT;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    private final DSLContext dslContext;

    @Autowired
    public OrderRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Collection<OrdersRecord> selectAllOrders(OrderFilter orderFilter) {
        return this.dslContext
            .selectFrom(ORDERS)
            .where(ORDERS.ID.eq(orderFilter.getLastItemId()))
            .and(ORDERS.RECEIVER.likeIgnoreCase("%" + orderFilter.getReceiver() + "%"))
            .orderBy(ORDERS.ID)
            .seek(orderFilter.getLastItemId())
            .limit(orderFilter.getLimit())
            .stream()
            .toList();
    }

    @Override
    public int countAllOrders(OrderFilter orderFilter) {
        return this.dslContext.fetchCount(
            this.dslContext
                .selectFrom(ORDERS)
                .where(ORDERS.ID.eq(orderFilter.getLastItemId()))
                .and(ORDERS.RECEIVER.likeIgnoreCase("%" + orderFilter.getReceiver() + "%"))
        );
    }

    @Override
    public OrdersRecord newRecord(OrderDTO orderDTO) {
        return this.dslContext.newRecord(ORDERS, orderDTO).setId(StringUtils.generateUUID());
    }

    @Override
    public void store(OrderDTO orderDTO) {
        this.dslContext.newRecord(ORDERS, orderDTO).store();
    }

    @Override
    public void storeOrderProduct(List<OrderProductDTO> orderedItems) {

        this.dslContext
            .insertInto(ORDER_PRODUCT)
            .values(orderedItems.stream().map(DSL::row))
            .execute();
    }
}
