package apap.tutorial.bacabaca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBukuResponseDTO {
    private UUID id;
    private String judul;
    private String tahunTerbit;
    private BigDecimal harga;
    private String namaPenerbit;
}
