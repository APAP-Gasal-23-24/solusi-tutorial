package apap.tutorial.bacabaca.dto.response;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class TranslateResponseDTO {
    private String status;
    private DataResponseDTO data;
}
