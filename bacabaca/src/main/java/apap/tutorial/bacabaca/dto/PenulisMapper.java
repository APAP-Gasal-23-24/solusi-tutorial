package apap.tutorial.bacabaca.dto;

import apap.tutorial.bacabaca.dto.request.CreatePenulisRequestDTO;
import apap.tutorial.bacabaca.model.Penulis;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PenulisMapper {
    Penulis createPenulisRequestDTOToPenulis(CreatePenulisRequestDTO createPenulisRequestDTO);
}
