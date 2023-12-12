package apap.tutorial.bacabaca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String role;
}
