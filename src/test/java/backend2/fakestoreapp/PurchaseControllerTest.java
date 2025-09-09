package backend2.fakestoreapp;

import backend2.fakestoreapp.DTO.PurchaseRegistrationDto;
import backend2.fakestoreapp.controller.PurchaseController;
import backend2.fakestoreapp.service.impl.PurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class PurchaseControllerTest {

    private PurchaseController purchaseController;

    @Mock
    private PurchaseServiceImpl purchaseServiceImpl;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        purchaseController = new PurchaseController(purchaseServiceImpl);
    }

    @Test
    void createPurchase_WhenAuthenticated_ShouldExtractEmailCorrectly() {
        PurchaseRegistrationDto purchaseRegistrationDTO = new PurchaseRegistrationDto();
        String expectedEmail = "test@example.com";

        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn(expectedEmail);

        String result = purchaseController.createPurchase(purchaseRegistrationDTO, authentication);

        verify(purchaseServiceImpl).createPurchase(purchaseRegistrationDTO, expectedEmail);
        assertEquals("redirect:/products", result);
    }

    @Test
    void createPurchase_WhenNotAuthenticated_ShouldNotCallService() {
        PurchaseRegistrationDto purchaseRegistrationDTO = new PurchaseRegistrationDto();
        when(authentication.isAuthenticated()).thenReturn(false);

        String result = purchaseController.createPurchase(purchaseRegistrationDTO, authentication);

        verify(purchaseServiceImpl, never()).createPurchase(any(), any());
        assertEquals("redirect:/login", result);
    }

}
