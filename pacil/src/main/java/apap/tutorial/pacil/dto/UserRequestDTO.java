package apap.tutorial.pacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
    private String username;
    private String name;
    private String password;
    private String role;
}
