package backend2.fakestoreapp.controller;

import backend2.fakestoreapp.service.impl.PurchaseServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PurchaseServiceImpl purchaseServiceImpl;

    public AdminController(PurchaseServiceImpl purchaseServiceImpl) {
        this.purchaseServiceImpl = purchaseServiceImpl;
    }

    @PostMapping("/delete/{id}")
    public String deletePurchase (@PathVariable Long id) {
        purchaseServiceImpl.deletePurchase(id);
        return "redirect:/admin/orders";
    }

    @GetMapping("/orders")
    public String getAllOrders (Model model){
        var allOrders = purchaseServiceImpl.getAllOrders();
        model.addAttribute("allOrders", allOrders);
        return "orders";
    }

}
