package backend2.fakestoreapp.service;

import backend2.fakestoreapp.DTO.CustomerRegistrationDTO;
import backend2.fakestoreapp.DTO.CustomerResponseDTO;


public interface CustomerService {
    CustomerResponseDTO register(CustomerRegistrationDTO dto);
}
