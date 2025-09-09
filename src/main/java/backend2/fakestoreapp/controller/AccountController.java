package backend2.fakestoreapp.controller;

import backend2.fakestoreapp.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final PurchaseRepository purchaseRepository;

    @GetMapping("/profile")
    public String profile(Model model, Authentication auth) {
        var orders = purchaseRepository.findByCustomerEmailOrderByCreatedAtDesc(auth.getName());
        model.addAttribute("orders", orders);
        return "profile";
    }
}
