package backend2.fakestoreapp.controller;

import backend2.fakestoreapp.DTO.PurchaseDto;
import backend2.fakestoreapp.service.impl.PurchaseServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PurchaseController {

    private final PurchaseServiceImpl purchaseServiceImpl;

    public PurchaseController(PurchaseServiceImpl purchaseServiceImpl) {
        this.purchaseServiceImpl = purchaseServiceImpl;
    }

    @PostMapping("/checkout")
    public String createPurchase(@ModelAttribute ("purchase") PurchaseDto purchaseDto, Authentication authentication){

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            purchaseServiceImpl.createPurchase(purchaseDto, email);
            return "redirect:/profile";
        }

        return "redirect:/products";
    }


}
