package backend2.fakestoreapp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegistrationDTO {

    @NotBlank(message = "Fältet får inte tomt.")
    @Size(min = 2, max = 100, message = "Namn måste vara mellan 2 och 100 tecken.")
    private String name;

    @NotBlank(message = "Fältet får inte tomt.")
    @Size(max = 255, message = "Adress får inte vara längre än 255 tecken.")
    private String address;

    @NotBlank(message = "Telefon får inte vara tomt.")
    @Pattern(
            regexp = "^[0-9+\\- ]{7,20}$",
            message = "Telefonnummer får bara innehålla '+' och '-', siffror och mellanslag."
    )
    private String phone;

    @NotBlank(message = "E-post får inte vara tomt.")
    @Email(message = "E-post är inte giltig.")
    private String email;

    @NotBlank(message = "Lösenord får inte vara tomt.")
    @Size(min = 6, message = "Lösenord måste vara minst 6 tecken.")
    // Om ni vill ha regex: minst en bokstav och en siffra:
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$",
            message = "Lösenord måste innehålla minst en bokstav och en siffra."
    )
    private String password;
}
