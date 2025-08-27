package backend2.fakestoreapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id")
    private Long articleId;
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

    @OneToMany (mappedBy = "product")
    private Set<Purchase> purchases = new HashSet<>();

}
