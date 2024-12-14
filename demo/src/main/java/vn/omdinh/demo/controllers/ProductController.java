package vn.omdinh.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.exceptions.ExceptionResponse;
import vn.omdinh.demo.exceptions.HttpException;
import vn.omdinh.demo.models.requests.PaginatedSearch;
import vn.omdinh.demo.models.requests.ProductFilter;
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
    @Operation(summary = "Create a new product")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Create success",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }
            ),
            @ApiResponse(responseCode = "401",
                description = "Invalid request body format",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
            @ApiResponse(responseCode = "500",
                description = "Internal server error",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
        }
    )
    ResponseEntity<ProductDTO> addNew(
        @RequestPart("request") ProductRequest request,
        @RequestPart("thumbnail") MultipartFile thumbnail
    ) throws IOException {
        return ResponseEntity
            .status(HttpStatusCode.valueOf(HttpStatus.CREATED.value()))
            .body(this.productService.createNew(request, thumbnail));
    }

    @GetMapping
    ResponseEntity<PaginatedResultResponse<Collection<ProductDTO>>> selectAllProducts(ProductFilter productFilter) {
        return ResponseEntity.ok().body(this.productService.selectAllProducts(productFilter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets product by ID")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Ok",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Product was not found",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
            @ApiResponse(responseCode = "500",
                description = "Internal server error",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
        }
    )
    ResponseEntity<ProductDTO> selectOneById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.productService.selectOneById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product by ID")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Update success",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Product was not found",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
            @ApiResponse(responseCode = "500",
                description = "Internal server error",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
        }
    )
    ResponseEntity<ProductDTO> updateOneById(
        @PathVariable String id,
        @RequestBody @Valid ProductRequest productRequest,
        @RequestPart MultipartFile thumbnail
    ) throws IOException, NoSuchFieldException, IllegalAccessException {
        var productDTO = ProductDTO.from(productRequest, thumbnail);

        return ResponseEntity.ok().body(this.productService.updateOneById(id, productDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by ID")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Delete success",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Product was not found",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
            @ApiResponse(responseCode = "500",
                description = "Internal server error",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
        }
    )
    ResponseEntity<String> deleteOneById(@PathVariable String id) {
        this.productService.deleteOneById(id);

        return ResponseEntity.ok().body("Delete successful!");
    }
}
