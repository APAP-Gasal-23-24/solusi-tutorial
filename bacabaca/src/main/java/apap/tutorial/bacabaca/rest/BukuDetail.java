package apap.tutorial.bacabaca.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BukuDetail {
    private  String status;

    @JsonProperty("valid-until")
    private String validUntil;
}
