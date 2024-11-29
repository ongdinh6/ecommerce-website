package vn.omdinh.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    String id;
    String title;
    Double price;
    Double discount;
    String categories;
    String description;
    Integer size;
    String status;
    String thumbnail;
    Boolean published;
}
