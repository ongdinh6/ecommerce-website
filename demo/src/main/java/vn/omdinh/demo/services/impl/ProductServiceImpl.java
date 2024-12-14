package vn.omdinh.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.exceptions.NotFoundException;
import vn.omdinh.demo.models.requests.ProductFilter;
import vn.omdinh.demo.models.requests.ProductItem;
import vn.omdinh.demo.models.requests.ProductRequest;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;
import vn.omdinh.demo.models.responses.ProductItemResponse;
import vn.omdinh.demo.repositories.impl.ProductRepositoryImpl;
import vn.omdinh.demo.services.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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

    public PaginatedResultResponse<Collection<ProductDTO>> selectAllProducts(ProductFilter productFilter) {
        var total = this.repository.countSelectAllProducts(productFilter);
        var products = this.repository.selectAllProducts(productFilter);

        if (!products.isEmpty()) {
            productFilter.setLastItemId(products.stream().toList().get(products.size() - 1).getId());
        }

        return new PaginatedResultResponse<>(
            total,
            productFilter.getLimit(),
            total / productFilter.getLimit(),
            productFilter.getLimit(),
            productFilter,
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
        this.repository.deleteOneById(this.selectOneById(id).getId());
    }

    @Override
    public Collection<ProductDTO> selectProductsById(List<String> ids) {
        Collection<ProductDTO> productDTOS = new ArrayList<>();

        for (String id : ids) {
            productDTOS.add(this.selectOneById(id));
        }

        return productDTOS;
    }

    @Override
    public Collection<ProductItemResponse> calculateCost(Collection<ProductItem> items) {
        var products = this.selectProductsById(items.stream().map(ProductItem::productId).toList());

        Collection<ProductItemResponse> productItemResponses = new ArrayList<>();

        for (ProductDTO productDTO : products) {
            items.forEach(item -> {
                if (item.productId().equals(productDTO.getId())) {
                    productItemResponses.add(new ProductItemResponse(
                        productDTO.getId(),
                        item.amount(),
                        item.amount() * productDTO.getPrice(),
                        item.amount() * productDTO.getDiscount(),
                        (productDTO.getPrice() - productDTO.getDiscount()) * item.amount()
                    ));
                }
            });
        }

        return productItemResponses;
    }
}
