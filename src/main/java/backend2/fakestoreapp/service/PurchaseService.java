package backend2.fakestoreapp.service;

public interface PurchaseService {
    Long placeOrder(String customerEmail, Long productId);
}