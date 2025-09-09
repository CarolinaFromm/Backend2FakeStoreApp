package backend2.fakestoreapp.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private Long purchaseId;
    private Long productId;
    private String title;
    private double price;
    private String image;
    private LocalDateTime createdAt;

}
