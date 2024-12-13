package vn.omdinh.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.exceptions.NotFoundException;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.requests.ProductRequest;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;
import vn.omdinh.demo.repositories.impl.ProductRepositoryImpl;
import vn.omdinh.demo.services.ProductService;

import java.io.IOException;
import java.util.Collection;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepositoryImpl repository;

    @Autowired
    public ProductServiceImpl(ProductRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public ProductDTO createNew(ProductRequest request, MultipartFile file) throws IOException {
        ProductDTO productDTO = ProductDTO.from(request, file);

        var productRecord = this.repository.newRecord(productDTO);
        var savedProduct = productRecord.into(ProductDTO.class);
        this.repository.store(savedProduct);

        return savedProduct;
    }

    @Override
    public PaginatedResultResponse<Collection<ProductDTO>> selectAllProducts(PaginatedSearch paginatedSearch) {
        var total = this.repository.countSelectAllProducts(paginatedSearch);
        var products = this.repository.selectAllProducts(paginatedSearch);

//        if (!products.isEmpty()) {
            paginatedSearch.setLastItemId(products.stream().toList().get(products.size() - 1).getId());
//        }

        return new PaginatedResultResponse<>(
            total,
            paginatedSearch.getLimit(),
            total / paginatedSearch.getLimit(),
            paginatedSearch.getLimit(),
            paginatedSearch,
            products.stream().map(p -> p.into(ProductDTO.class)).toList()
        );
    }

    @Override
    public ProductDTO selectOneById(String id) {
        var record1 = this.repository.findOneById(id);

        if (record1 == null) {
            throw new NotFoundException("Not found a product has id [%s]".formatted(id));
        }

        return record1.into(ProductDTO.class);
    }

    @Override
    public ProductDTO updateOneById(String id, ProductDTO updatedDTO) throws NoSuchFieldException, IllegalAccessException {
        var updatedDto = this.selectOneById(id);

        var dto = updatedDto.update(updatedDTO);

        this.repository.store(dto);

        return dto;
    }

    @Override
    public void deleteOneById(String id) {
        var record1 = this.repository.findOneById(id);

        if (record1 == null) {
            throw new NotFoundException("Not found a product has id [%s]".formatted(id));
        }

        this.repository.deleteOneById(id);
    }

}
