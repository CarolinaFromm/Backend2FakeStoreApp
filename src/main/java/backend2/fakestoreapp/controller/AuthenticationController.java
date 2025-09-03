package backend2.fakestoreapp.controller;

import backend2.fakestoreapp.DTO.CustomerRegistrationDTO;
import backend2.fakestoreapp.DTO.LoginDTO;
import backend2.fakestoreapp.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class AuthenticationController {

    private final CustomerService customerService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new CustomerRegistrationDTO());
        return "createNew";
    }

    @PostMapping("/register")
    public String handleRegister(
            @Valid @ModelAttribute("customer") CustomerRegistrationDTO form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "createNew";
        }

        try {
            customerService.register(form);
            return "redirect:/login?registered";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "createNew";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
