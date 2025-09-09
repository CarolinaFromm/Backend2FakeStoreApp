package backend2.fakestoreapp.controller;

import backend2.fakestoreapp.DTO.PurchaseDeleteDTO;
import backend2.fakestoreapp.DTO.PurchaseDto;
import backend2.fakestoreapp.service.impl.PurchaseServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PurchaseServiceImpl purchaseServiceImpl;

    public AdminController(PurchaseServiceImpl purchaseServiceImpl) {
        this.purchaseServiceImpl = purchaseServiceImpl;
    }

    @PostMapping("/delete")
    public String deletePurchase (@ModelAttribute("purchase") PurchaseDeleteDTO purchaseDeleteDTO) {
        purchaseDeleteDTO.getPurchaseIds().forEach(id -> purchaseServiceImpl.deletePurchase(id));
        return "deleted";
    }

}
