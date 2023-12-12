package apap.tutorial.bacabaca.dto;

import apap.tutorial.bacabaca.dto.request.CreateUserRequestDTO;
import apap.tutorial.bacabaca.dto.response.CreateUserResponseDTO;
import apap.tutorial.bacabaca.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    UserModel createUserRequestDTOToUserModel(CreateUserRequestDTO createUserRequestDTO);

    @Mapping(target = "role", ignore = true)
    CreateUserResponseDTO createUserResponseDTOToUserModel(UserModel userModel);
}
