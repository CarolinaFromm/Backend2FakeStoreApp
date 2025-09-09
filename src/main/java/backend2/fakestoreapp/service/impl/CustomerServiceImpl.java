package backend2.fakestoreapp.service.impl;

import backend2.fakestoreapp.DTO.CustomerRegistrationDTO;
import backend2.fakestoreapp.DTO.CustomerResponseDTO;
import backend2.fakestoreapp.model.Customer;
import backend2.fakestoreapp.model.Role;
import backend2.fakestoreapp.repository.CustomerRepository;
import backend2.fakestoreapp.repository.RoleRepository;
import backend2.fakestoreapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CustomerResponseDTO register(CustomerRegistrationDTO dto) {
        String email = dto.getEmail().trim().toLowerCase();
        if (customerRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("E-postadressen är redan registrerad.");
        }

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());
        customer.setEmail(email);
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));

        String selected = dto.getRole() == null ? "user" : dto.getRole().toLowerCase();
        Role role = roleRepository.findByName(selected)
                .orElseThrow(() -> new IllegalArgumentException("Rollen " + selected + "finns inte."));

        customer.getRoles().add(role);

        Customer saved = customerRepository.save(customer);

        CustomerResponseDTO result = new CustomerResponseDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setEmail(saved.getEmail());
        return result;
    }
}
