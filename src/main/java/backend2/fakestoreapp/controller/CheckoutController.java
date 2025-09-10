package backend2.fakestoreapp.controller;

import backend2.fakestoreapp.DTO.PurchaseRegistrationDto;
import backend2.fakestoreapp.model.Customer;
import backend2.fakestoreapp.model.Product;
import backend2.fakestoreapp.repository.CustomerRepository;
import backend2.fakestoreapp.repository.ProductRepository;
import backend2.fakestoreapp.service.impl.PurchaseServiceImpl;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final PurchaseServiceImpl purchaseService;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @GetMapping("/checkout")
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


    @PostMapping("/confirm")
    public String createPurchase(@ModelAttribute PurchaseRegistrationDto purchaseRegistrationDto,
                                 Authentication authentication, RedirectAttributes redirectAttributes){

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            purchaseService.createPurchase(purchaseRegistrationDto.getProduct_article_id(), email);
            redirectAttributes.addFlashAttribute("message", "Order # " + "skapad");
            return "redirect:/profile";
        }

        return "redirect:/login";
    }

}
