package vn.omdinh.demo.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotEmpty(message = "Cannot create an order without no items!")
    Collection<ProductItem> items;

    @NotNull(message = "Name is required!")
    @NotBlank(message = "Name is required!")
    String receiver;

    @NotNull(message = "Phone number is required!")
    @NotBlank(message = "Phone number is required!")
    String phoneNumber;

    @NotNull(message = "Delivery address is required!")
    @NotBlank(message = "Delivery address is required!")
    String addressDelivery;

    String note;
}


