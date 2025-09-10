package backend2.fakestoreapp.controller;

import backend2.fakestoreapp.model.Customer;
import backend2.fakestoreapp.model.Product;
import backend2.fakestoreapp.repository.CustomerRepository;
import backend2.fakestoreapp.repository.ProductRepository;
import backend2.fakestoreapp.service.PurchaseService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final PurchaseService purchaseService;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @GetMapping("/orders/checkout")
    public String checkout(@RequestParam("productId") Long productId,
                           Authentication authentication,
                           Model model) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String email = authentication.getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Kund saknas: " + email));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Produkt saknas: " + productId));

        model.addAttribute("customer", customer);
        model.addAttribute("product", product);
        return "checkout";
    }

    @PostMapping("/orders/confirm")
    public String confirm(@RequestParam("productId") Long productId,
                          Authentication authentication,
                          RedirectAttributes redirectAttributes) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String email = authentication.getName();
        Long orderId = purchaseService.placeOrder(email, productId);

        redirectAttributes.addFlashAttribute("message", "Order #" + orderId + "skapad.");
        return "redirect:/profile";
    }

}
