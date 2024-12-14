package vn.omdinh.demo.dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO {
    String productId;
    String orderId;
    int amount;
}
