package apap.tutorial.bacabaca.dto.request;

import apap.tutorial.bacabaca.model.Penerbit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import apap.tutorial.bacabaca.model.Penulis;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
    private List<Penulis> listPenulis;
}
