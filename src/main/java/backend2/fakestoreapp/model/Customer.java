package backend2.fakestoreapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private String address;
    private String email;
    private String phone;
    private String password;

    @OneToMany (mappedBy = "customer")
    private Set<Purchase> purchases = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set <Role> roles = new HashSet<>();


}
