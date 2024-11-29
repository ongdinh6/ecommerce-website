package vn.omdinh.demo.models.requests;

import lombok.Data;

@Data
public class ProductRequest {
    String title;
    double price;
    double discount;
    String categories;
    String description;
    byte[] thumbnail;
    boolean published;
}
