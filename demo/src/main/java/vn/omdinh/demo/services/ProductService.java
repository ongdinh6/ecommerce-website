package vn.omdinh.demo.services;

import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.requests.ProductFilter;
import vn.omdinh.demo.models.requests.ProductItem;
import vn.omdinh.demo.models.requests.ProductRequest;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;
import vn.omdinh.demo.models.responses.ProductItemResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface ProductService {
    ProductDTO createNew(ProductRequest request, MultipartFile file) throws IOException;
    PaginatedResultResponse<Collection<ProductDTO>> selectAllProducts(ProductFilter productFilter);
    ProductDTO selectOneById(String id);
    ProductDTO updateOneById(String id, ProductDTO updatedDTO) throws NoSuchFieldException, IllegalAccessException;
    void deleteOneById(String id);
    Collection<ProductDTO> selectProductsById(List<String> ids);
    Collection<ProductItemResponse> calculateCost(Collection<ProductItem> items);
}
