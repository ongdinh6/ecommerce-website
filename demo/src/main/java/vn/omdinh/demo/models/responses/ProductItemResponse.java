package vn.omdinh.demo.models.responses;

public record ProductItemResponse(
    String id,
    int amount,
    double totalPrice,
    double discount,
    double finalCost
) {
}
