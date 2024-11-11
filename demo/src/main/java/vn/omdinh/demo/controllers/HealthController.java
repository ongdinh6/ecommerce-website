package vn.omdinh.demo.controllers;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static nu.studer.sample.tables.Product.PRODUCT;


@RestController
@RequestMapping("")
public class HealthController {
    private final DSLContext dslContext;

    @Autowired public HealthController(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @GetMapping("/status")
    String status() {
        return "Hello world!";
    }

    @GetMapping("/products")
    Integer allProducts() {
        return dslContext.selectCount().from(PRODUCT).fetchOne(0, int.class);
    }
}
