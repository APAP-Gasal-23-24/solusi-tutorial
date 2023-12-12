package apap.tutorial.bacabaca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TranslateDTO {
    private String status;
    private String message;
    private DataDTO data;
}
