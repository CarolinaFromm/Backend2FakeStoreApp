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
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role userRole = roleRepository.
                findByName("user")
                .orElseThrow(() -> new IllegalArgumentException("User-role 'user' does not exist"));

        Customer c = new Customer();
        c.setName(dto.getName());
        c.setAddress(dto.getAddress());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        c.setPassword(passwordEncoder.encode(dto.getPassword()));
        c.getRoles().add(userRole);

        Customer saved = customerRepository.save(c);

        CustomerResponseDTO result = new CustomerResponseDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setEmail(saved.getEmail());
        return result;
    }
}
