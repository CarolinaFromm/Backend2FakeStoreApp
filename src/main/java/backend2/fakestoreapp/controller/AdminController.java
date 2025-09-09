package backend2.fakestoreapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/admin")
public class AdminController {

    @PostMapping("/delete")
    public String deletePurchase () {

        return "deleted";
    }

}
