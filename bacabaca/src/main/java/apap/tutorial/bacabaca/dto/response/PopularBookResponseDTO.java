package apap.tutorial.bacabaca.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class PopularBookResponseDTO {
    private String book_id;
    private int position;
    private String name;
    private String cover;
    private Long rating;
    private String url;
}
