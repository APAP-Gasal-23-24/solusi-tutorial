package apap.tutorial.bacabaca.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePenerbitRequestDTO extends CreatePenerbitRequestDTO{
//    @NotNull
    private long idPenerbit;
}
