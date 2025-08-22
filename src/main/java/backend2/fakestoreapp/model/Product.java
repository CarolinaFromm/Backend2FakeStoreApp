package backend2.fakestoreapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;

    @Column(name = "image")
    private String imageUrl;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "rate", column = @Column(name = "rate")),
            @AttributeOverride(name = "count", column = @Column(name = "count"))
    })
    private Rating rating;

}
