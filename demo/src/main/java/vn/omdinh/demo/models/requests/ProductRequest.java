package vn.omdinh.demo.models.requests;

public record ProductRequest(
    String name,
    byte[] thumbnail,
    String description,
    String category,
    double price,
    String type,
    double discount,
    boolean published
) {}
