package apap.tutorial.bacabaca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import apap.tutorial.bacabaca.model.Sertifikasi;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePenulisRequestDTO {
    @NotBlank
    private String namaPenulis;
    @NotBlank
    private String biografi;
    // setelah panduan selesai, di bagian resources
    @NotEmpty
    private List<Sertifikasi> listSertifikasi;
}
