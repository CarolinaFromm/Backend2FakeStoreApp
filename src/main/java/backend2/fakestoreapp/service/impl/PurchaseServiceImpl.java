package backend2.fakestoreapp.service.impl;

import backend2.fakestoreapp.model.Purchase;
import backend2.fakestoreapp.repository.CustomerRepository;
import backend2.fakestoreapp.repository.ProductRepository;
import backend2.fakestoreapp.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl{

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public void createPurchase(Long articleId, String email) {

        var product = productRepository.findByArticleId(articleId)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        var customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        var purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setProduct(product);
        purchase.setCreatedAt(LocalDateTime.now());
        purchaseRepository.save(purchase);
    }

    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }

    public List<Purchase> getOrdersByCustomerEmail (String email){
        return purchaseRepository.findByCustomerEmailOrderByCreatedAtDesc(email);
    }

    public List<Purchase> getAllOrders() {
        return purchaseRepository.findAll();
    }
}
