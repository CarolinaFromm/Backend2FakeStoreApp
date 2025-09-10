package backend2.fakestoreapp.controller;

import backend2.fakestoreapp.repository.PurchaseRepository;
import backend2.fakestoreapp.service.impl.PurchaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final PurchaseServiceImpl purchaseService;

    @GetMapping("/profile")
    public String profile(Model model, Authentication auth) {
        var orders = purchaseService.getOrdersByCustomerEmail(auth.getName());
        model.addAttribute("orders", orders);
        return "profile";
    }
}
