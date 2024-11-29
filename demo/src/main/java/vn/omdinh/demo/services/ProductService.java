package vn.omdinh.demo.services;

import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.models.requests.ProductRequest;

import java.io.IOException;
import java.util.Collection;

public interface ProductService {
    ProductDTO createNew(ProductRequest request, MultipartFile file) throws IOException;
    Collection<ProductDTO> selectAllProducts();
    ProductDTO selectOneById(String id);
}
