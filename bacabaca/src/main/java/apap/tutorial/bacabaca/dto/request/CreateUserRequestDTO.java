package apap.tutorial.bacabaca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequestDTO {
    private String username;
    private String name;
    private String password;
    private String role;
}
