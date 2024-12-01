package vn.omdinh.demo.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.requests.ProductRequest;
import vn.omdinh.demo.models.responses.PaginatedResultResponse;
import vn.omdinh.demo.services.ProductService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/new", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    ResponseEntity<ProductDTO> addNew(
        @RequestPart("request") ProductRequest request,
        @RequestPart("thumbnail") MultipartFile thumbnail
    ) throws IOException {
        return ResponseEntity
            .status(HttpStatusCode.valueOf(HttpStatus.CREATED.value()))
            .body(this.productService.createNew(request, thumbnail));
    }

    @GetMapping
    ResponseEntity<PaginatedResultResponse<Collection<ProductDTO>>> selectAllProducts(
        @Valid @RequestBody(required = false) PaginatedSearch paginatedSearch
    ) {
        PaginatedSearch sanitizedPaginatedSearch = new PaginatedSearch();
        if (paginatedSearch != null) {
            sanitizedPaginatedSearch = paginatedSearch;
        }
        return ResponseEntity.ok().body(this.productService.selectAllProducts(sanitizedPaginatedSearch));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> selectOneById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.productService.selectOneById(id));
    }
}
