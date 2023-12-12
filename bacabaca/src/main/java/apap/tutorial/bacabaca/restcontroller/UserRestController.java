package apap.tutorial.bacabaca.restcontroller;

import apap.tutorial.bacabaca.dto.UserMapper;
import apap.tutorial.bacabaca.dto.request.CreateUserRequestDTO;
import apap.tutorial.bacabaca.dto.response.CreateUserResponseDTO;
import apap.tutorial.bacabaca.model.UserModel;
import apap.tutorial.bacabaca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;
    @PostMapping(value = "/user/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> addUser(@RequestBody CreateUserRequestDTO createUserRequestDTO){
        try{
            UserModel userModel = userMapper.createUserRequestDTOToUserModel(createUserRequestDTO);
            userModel = userService.addUser(userModel, createUserRequestDTO);

            CreateUserResponseDTO createUserResponseDTO = userMapper.createUserResponseDTOToUserModel(userModel);
            createUserResponseDTO.setRole(userModel.getRole().getRole());
            return new ResponseEntity<>(createUserResponseDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }
}
