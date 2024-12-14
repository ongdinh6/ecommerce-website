package vn.omdinh.demo.dtos;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String receiver;
    private double totalCost;
    private int totalItems;
    private String addressDelivery;
    private String phoneNumber;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
