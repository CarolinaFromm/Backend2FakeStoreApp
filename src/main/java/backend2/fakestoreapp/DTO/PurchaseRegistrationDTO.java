package backend2.fakestoreapp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseRegistrationDTO {

//    private Long customerId;
    private Long product_article_id;
    private LocalDateTime createdAt;

}
