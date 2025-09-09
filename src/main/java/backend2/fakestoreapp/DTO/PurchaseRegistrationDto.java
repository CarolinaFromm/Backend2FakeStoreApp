package backend2.fakestoreapp.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseRegistrationDto {

    private Long product_article_id;
    private LocalDateTime createdAt;

}
