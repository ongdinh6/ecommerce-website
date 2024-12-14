package vn.omdinh.demo.repositories;

import jakarta.annotation.Nullable;
import nu.studer.sample.tables.records.ProductsRecord;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.requests.ProductFilter;

import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    Collection<ProductsRecord> selectAllProducts(ProductFilter productFilter);
    @Nullable
    ProductsRecord findOneById(String id);
    void store(ProductDTO productDTO);
    ProductsRecord newRecord(ProductDTO productDTO);
    void deleteOneById(String id);
    Collection<ProductsRecord> selectProductsById(List<String> ids);
}
