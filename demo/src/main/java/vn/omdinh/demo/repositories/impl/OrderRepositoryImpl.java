package vn.omdinh.demo.repositories.impl;

import vn.omdinh.tables.records.OrdersRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.omdinh.demo.dtos.OrderDTO;
import vn.omdinh.demo.dtos.OrderProductDTO;
import vn.omdinh.demo.exceptions.InternalServerError;
import vn.omdinh.demo.models.requests.OrderFilter;
import vn.omdinh.demo.repositories.OrderRepository;
import vn.omdinh.demo.utils.StringUtils;

import java.util.Collection;
import java.util.List;

import static vn.omdinh.tables.Orders.ORDERS;
import static vn.omdinh.tables.OrderProduct.ORDER_PRODUCT;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(OrderRepositoryImpl.class);
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
    public void store(OrderDTO orderDTO, List<OrderProductDTO> orderedItems) {
        try {
            this.dslContext.transaction(ctx -> {
                ctx.dsl().newRecord(ORDERS, orderDTO).store();

                orderedItems.forEach(item -> {
                    ctx.dsl().insertInto(ORDER_PRODUCT)
                        .values(item.getOrderId(), item.getProductId(), item.getAmount())
                        .execute();
                });

                ctx.dsl().commit().execute();
            });
        } catch (Exception e) {
            logger.error(e);
            this.dslContext.rollback().execute();
            throw new InternalServerError("Store order occurred an error! Error: %s".formatted(e.getMessage()));
        }
    }
}
