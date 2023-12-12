package apap.tutorial.bacabaca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestFlutterDTO {
    private String username;
    private String password;
}
