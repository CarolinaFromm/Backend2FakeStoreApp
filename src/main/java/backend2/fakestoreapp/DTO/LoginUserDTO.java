package backend2.fakestoreapp.DTO;
import lombok.Data;

@Data
public class LoginUserDTO {
    public String email;
    public String password;
    public String role;
}
