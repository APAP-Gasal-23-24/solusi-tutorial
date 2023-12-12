package apap.tutorial.bacabaca.dto.request;

import apap.tutorial.bacabaca.model.Penulis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteMultiplePenulisDTO {
    private List<Penulis> listPenulis;
}
