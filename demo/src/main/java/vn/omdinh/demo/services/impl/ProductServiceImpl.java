package vn.omdinh.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.exceptions.NotFoundException;
import vn.omdinh.demo.models.requests.ProductRequest;
import vn.omdinh.demo.repositories.ProductRepository;
import vn.omdinh.demo.services.ProductService;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDTO createNew(ProductRequest request, MultipartFile file) throws IOException {
        var record1 = this.repository.newRecord(request).setThumbnail(Base64.getEncoder().encode(file.getBytes()));
        var dto = record1.into(ProductDTO.class);
        this.repository.store(dto);
        return dto;
    }

    @Override
    public Collection<ProductDTO> selectAllProducts() {
        return this.repository.selectAllProducts();
    }

    @Override
    public ProductDTO selectOneById(String id) {
        var record1 = this.repository.selectOne(id);

        if (record1 == null) {
            throw new NotFoundException("Not found a product has id {id}");
        }

        return record1.into(ProductDTO.class);
    }

}
