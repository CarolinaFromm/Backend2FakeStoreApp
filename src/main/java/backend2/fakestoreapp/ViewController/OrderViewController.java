package backend2.fakestoreapp.ViewController;

import backend2.fakestoreapp.service.impl.PurchaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderViewController {
    private final PurchaseServiceImpl purchaseService;

//    @GetMapping("/orders")
//    public String showOrders(Model model){
//        return "orders";
//    }
}
