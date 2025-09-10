package backend2.fakestoreapp.service.impl;

import backend2.fakestoreapp.model.Customer;
import backend2.fakestoreapp.model.Product;
import backend2.fakestoreapp.model.Purchase;
import backend2.fakestoreapp.repository.CustomerRepository;
import backend2.fakestoreapp.repository.ProductRepository;
import backend2.fakestoreapp.repository.PurchaseRepository;
import backend2.fakestoreapp.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public Long placeOrder(String customerEmail, Long productId) {
        Customer customer = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Kund saknas " + customerEmail));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produkt saknas " + productId));

        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setProduct(product);

        return purchaseRepository.save(purchase).getId();
    }
}
