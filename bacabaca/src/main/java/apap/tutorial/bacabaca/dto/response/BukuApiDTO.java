package apap.tutorial.bacabaca.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BukuApiDTO {
    private String name;
    private String position;
    private double rating;
}
