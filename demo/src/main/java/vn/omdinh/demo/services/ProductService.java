package vn.omdinh.demo.services;

import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.requests.ProductRequest;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;

import java.io.IOException;
import java.util.Collection;

public interface ProductService {
    ProductDTO createNew(ProductRequest request, MultipartFile file) throws IOException;
    PaginatedResultResponse<Collection<ProductDTO>> selectAllProducts(PaginatedSearch paginatedSearch);
    ProductDTO selectOneById(String id);
}
