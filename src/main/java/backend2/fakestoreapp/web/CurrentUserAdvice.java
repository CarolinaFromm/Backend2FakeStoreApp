package backend2.fakestoreapp.web;

import backend2.fakestoreapp.repository.CustomerRepository;
import backend2.fakestoreapp.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.Authentication;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class CurrentUserAdvice {

    private final CustomerRepository customers;

    @ModelAttribute("currentCustomer")
    public Customer addCurrentCustomer(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) return null;
        String email = auth.getName();
        return customers.findByEmail(email).orElse(null);
    }
}
