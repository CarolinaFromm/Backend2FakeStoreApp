package backend2.fakestoreapp.service.impl;

import backend2.fakestoreapp.DTO.PurchaseRegistrationDTO;
import backend2.fakestoreapp.model.Purchase;
import backend2.fakestoreapp.repository.CustomerRepository;
import backend2.fakestoreapp.repository.ProductRepository;
import backend2.fakestoreapp.repository.PurchaseRepository;
import backend2.fakestoreapp.service.PurchaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;


@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public void createPurchase(PurchaseRegistrationDTO purchaseRegistrationDTO, String email) {

        var product = productRepository.findByArticleId(purchaseRegistrationDTO.getProduct_article_id())
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        var customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        var purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setProduct(product);
        purchase.setCreatedAt(LocalDateTime.now());
        purchaseRepository.save(purchase);
    }
}
