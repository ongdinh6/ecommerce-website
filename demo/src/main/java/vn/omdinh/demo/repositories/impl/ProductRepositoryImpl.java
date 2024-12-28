package vn.omdinh.demo.repositories.impl;

import jakarta.annotation.Nullable;
import vn.omdinh.tables.records.ProductsRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.exceptions.InternalServerError;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.requests.ProductFilter;
import vn.omdinh.demo.repositories.ProductRepository;
import vn.omdinh.demo.utils.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static vn.omdinh.tables.OrderProduct.ORDER_PRODUCT;
import static vn.omdinh.tables.Products.PRODUCTS;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final DSLContext dslContext;

    @Autowired
    public ProductRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public int countSelectAllProducts(ProductFilter productFilter) {
        return this.dslContext.fetchCount(
            this.dslContext
                .selectFrom(PRODUCTS)
                .where(PRODUCTS.NAME.likeIgnoreCase("%" + productFilter.getQuery() + "%"))
        );
    }

    @Override
    public Collection<ProductsRecord> selectAllProducts(ProductFilter productFilter) {
        return this.dslContext
            .selectFrom(PRODUCTS)
            .where(PRODUCTS.NAME.likeIgnoreCase("%" + productFilter.getQuery() + "%"))
            .orderBy(PRODUCTS.ID)
            .seek(productFilter.getLastItemId())
            .limit(productFilter.getLimit())
            .stream()
            .toList();
    }

    @Override
    public @Nullable ProductsRecord findOneById(String id) {
        return this.dslContext.selectFrom(PRODUCTS).where(PRODUCTS.ID.eq(id)).fetchAny();
    }

    @Override
    public void store(ProductDTO dto) {
        this.dslContext.newRecord(PRODUCTS, dto).store();
    }

    @Override
    public ProductsRecord newRecord(ProductDTO productDTO) {
        return this.dslContext.newRecord(PRODUCTS, productDTO).setId(StringUtils.generateUUID());
    }

    @Override
    public void deleteOneById(String id) {
        try {
            this.dslContext.transaction((ctx) -> {
                ctx.dsl().deleteFrom(ORDER_PRODUCT).where(ORDER_PRODUCT.PRODUCTID.eq(id)).execute();
                ctx.dsl().deleteFrom(PRODUCTS).where(PRODUCTS.ID.eq(id)).execute();

                ctx.dsl().commit().execute();
            });
        } catch (Exception e) {
            // Rollback exception
            this.dslContext.rollback().execute();
            throw new InternalServerError("Delete a product occurred an error! Error: %s".formatted(e.getMessage()));
        }
    }


    @Override
    public Collection<ProductsRecord> selectProductsById(List<String> ids) {
        return this.dslContext
            .selectFrom(PRODUCTS)
            .where(String.valueOf(ids.stream().sorted().map(PRODUCTS.ID::eq)))
            .orderBy(PRODUCTS.ID)
            .stream()
            .toList();
    }
}
