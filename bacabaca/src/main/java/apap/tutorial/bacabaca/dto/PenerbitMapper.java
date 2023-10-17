package apap.tutorial.bacabaca.dto;

import apap.tutorial.bacabaca.controller.PenerbitController;
import apap.tutorial.bacabaca.dto.request.CreatePenerbitRequestDTO;
import apap.tutorial.bacabaca.dto.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.request.UpdatePenerbitRequestDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penerbit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PenerbitMapper {
    Penerbit createPenerbitRequestDTOToPenerbit(CreatePenerbitRequestDTO createPenerbitRequestDTO);

    Penerbit updatePenerbitRequestDTOToPenerbit(UpdatePenerbitRequestDTO updatePenerbitRequestDTO);
}
