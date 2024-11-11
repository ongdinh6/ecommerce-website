package vn.omdinh.demo.controllers;

import nu.studer.sample.tables.records.ProductRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.mapper.ProductMapper;

import java.util.List;

import static nu.studer.sample.tables.Product.PRODUCT;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final DSLContext dslContext;

    @Autowired
    public ProductController(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @GetMapping("")
    ResponseEntity<List<ProductDTO>> all() {
        Result<ProductRecord> products = dslContext.selectFrom(PRODUCT).fetch();

        return ResponseEntity.ok(products.stream().map(ProductMapper::toDTO).toList());
    }
}
