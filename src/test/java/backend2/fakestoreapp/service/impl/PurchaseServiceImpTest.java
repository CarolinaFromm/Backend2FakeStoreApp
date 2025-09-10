package backend2.fakestoreapp.service.impl;

import backend2.fakestoreapp.DTO.PurchaseRegistrationDto;
import backend2.fakestoreapp.model.Customer;
import backend2.fakestoreapp.model.Product;
import backend2.fakestoreapp.model.Purchase;
import backend2.fakestoreapp.repository.CustomerRepository;
import backend2.fakestoreapp.repository.ProductRepository;
import backend2.fakestoreapp.repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceImpTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    private Long articleId;
    private Product product;
    private Customer customer;
    private String customerEmail;

    @BeforeEach
    void setUp() {
        customerEmail = "test@example.com";

        articleId = 1L;

        product = new Product();
        product.setArticleId(articleId);

        customer = new Customer();
        customer.setEmail(customerEmail);
    }

    @Test
    void createPurchase_Success() {
        when(productRepository.findByArticleId(1L)).thenReturn(Optional.of(product));
        when(customerRepository.findByEmail(customerEmail)).thenReturn(Optional.of(customer));

        purchaseService.createPurchase(articleId, customerEmail);

        ArgumentCaptor<Purchase> purchaseCaptor = ArgumentCaptor.forClass(Purchase.class);

        verify(purchaseRepository).save(purchaseCaptor.capture());

        Purchase capturedPurchase = purchaseCaptor.getValue();

        assertNotNull(capturedPurchase);
        assertEquals(customer, capturedPurchase.getCustomer());
        assertEquals(product, capturedPurchase.getProduct());
        assertNotNull(capturedPurchase.getCreatedAt());
    }

    @Test
    void createPurchase_ProductNotFound() {
        when(productRepository.findByArticleId(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                purchaseService.createPurchase(articleId, customerEmail));

        verify(purchaseRepository, never()).save(any(Purchase.class));
    }

    @Test
    void createPurchase_CustomerNotFound() {
        when(productRepository.findByArticleId(1L)).thenReturn(Optional.of(product));
        when(customerRepository.findByEmail(customerEmail)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                purchaseService.createPurchase(articleId, customerEmail));

        verify(purchaseRepository, never()).save(any(Purchase.class));
    }
}
