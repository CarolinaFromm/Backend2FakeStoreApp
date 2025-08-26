package backend2.fakestoreapp.DTO;
import lombok.Data;

@Data
public class LoginDTOUser {
    public String email;
    public String password;
    public String role;
}
