package vn.omdinh.demo.repositories;

import jakarta.annotation.Nullable;
import nu.studer.sample.tables.records.ProductRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.requests.ProductRequest;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import static nu.studer.sample.tables.Product.PRODUCT;

@Repository
public class ProductRepository {
    private final DSLContext context;

    @Autowired
    public ProductRepository(DSLContext context) {
        this.context = context;
    }

    public Collection<ProductDTO> selectAllProducts(PaginatedSearch paginatedSearch) {
        return this.context
                .selectFrom(PRODUCT)
                .where(PRODUCT.TITLE.likeIgnoreCase("%" + paginatedSearch.getQuery() + "%"))
                .offset(paginatedSearch.getOffset())
                .limit(paginatedSearch.getLimit())
                .stream()
                .map(r -> r.into(ProductDTO.class))
                .collect(Collectors.toList());

    }

    public void store(ProductDTO dto) {
        this.context.newRecord(PRODUCT, dto).store();
    }

    @Nullable
    public Record selectOne(String id) {
        return this.context.select().from(PRODUCT).where(PRODUCT.ID.eq(id)).fetchOne();
    }

    public ProductRecord newRecord(ProductRequest request) {
        return this.context.newRecord(PRODUCT, request).setId(String.valueOf(UUID.randomUUID()));
    }
}
