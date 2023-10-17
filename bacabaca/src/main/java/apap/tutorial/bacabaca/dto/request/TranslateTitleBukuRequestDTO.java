package apap.tutorial.bacabaca.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TranslateTitleBukuRequestDTO {
    @NotNull
    private UUID bookId;
    @NotBlank
    private String sourceLanguage;
    @NotBlank
    private String targetLanguage;
}
