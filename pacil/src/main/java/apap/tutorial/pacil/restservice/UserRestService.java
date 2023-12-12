package apap.tutorial.pacil.restservice;

import apap.tutorial.pacil.dto.UserRequestDTO;
import apap.tutorial.pacil.dto.UserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface UserRestService {
    UserResponseDTO sendUser(UserRequestDTO userDTO, String jwtToken);
    String getToken(String username, String name);
}
