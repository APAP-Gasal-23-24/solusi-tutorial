package apap.tutorial.bacabaca.service;

import apap.tutorial.bacabaca.dto.request.CreateUserRequestDTO;
import apap.tutorial.bacabaca.dto.request.LoginJwtRequestDTO;
import apap.tutorial.bacabaca.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user, CreateUserRequestDTO createUserRequestDTO);
    
    
    String encrypt(String password);

    String loginJwtAdmin(LoginJwtRequestDTO loginJwtRequestDTO);
}
