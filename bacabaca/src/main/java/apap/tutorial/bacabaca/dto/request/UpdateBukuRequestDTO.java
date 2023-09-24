package apap.tutorial.bacabaca.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBukuRequestDTO extends CreateBukuRequestDTO{
    @NotNull
    private UUID id;
}
