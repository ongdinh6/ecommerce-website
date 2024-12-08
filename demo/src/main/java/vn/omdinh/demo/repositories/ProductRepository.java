package vn.omdinh.demo.repositories;

import jakarta.annotation.Nullable;
import nu.studer.sample.tables.records.ProductsRecord;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.models.requests.PaginatedSearch;

import java.util.Collection;

public interface ProductRepository {
    Collection<ProductsRecord> selectAllProducts(PaginatedSearch paginatedSearch);
    @Nullable
    ProductsRecord findOneById(String id);
    void store(ProductDTO productDTO);
    ProductsRecord newRecord(ProductDTO productDTO);
    void deleteOneById(String id);
}
