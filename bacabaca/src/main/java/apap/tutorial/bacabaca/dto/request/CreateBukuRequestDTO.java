package apap.tutorial.bacabaca.dto.request;

import apap.tutorial.bacabaca.model.Penerbit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBukuRequestDTO {
    @NotBlank
    private String judul;
    @NotBlank
    private String tahunTerbit;
    @Min(0L)
    private BigDecimal harga;
    @NotNull
    private Penerbit penerbit;
}
