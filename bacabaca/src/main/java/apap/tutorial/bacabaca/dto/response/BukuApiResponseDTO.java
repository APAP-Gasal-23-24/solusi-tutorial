package apap.tutorial.bacabaca.dto.response;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BukuApiResponseDTO {
    private List<BukuApiDTO> data;
}
