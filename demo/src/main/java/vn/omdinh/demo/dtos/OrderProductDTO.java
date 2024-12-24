package vn.omdinh.demo.dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO {
    String orderId;
    String productId;
    int amount;
}
