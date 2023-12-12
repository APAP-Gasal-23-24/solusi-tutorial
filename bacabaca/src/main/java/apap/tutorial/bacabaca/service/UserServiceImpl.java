package apap.tutorial.bacabaca.service;

import apap.tutorial.bacabaca.dto.request.CreateUserRequestDTO;
import apap.tutorial.bacabaca.dto.request.LoginJwtRequestDTO;
import apap.tutorial.bacabaca.model.UserModel;
import apap.tutorial.bacabaca.repository.UserDb;
import apap.tutorial.bacabaca.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserModel addUser(UserModel user, CreateUserRequestDTO createUserRequestDTO){
        user.setRole(roleService.getRoleByRoleName(createUserRequestDTO.getRole()));
        String hashedPass = encrypt(user.getPassword());
        user.setPassword(hashedPass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public String loginJwtAdmin(LoginJwtRequestDTO loginJwtRequestDTO) {
        String username = loginJwtRequestDTO.getUsername();
        String name = loginJwtRequestDTO.getName();

        UserModel user = userDb.findByUsername(username);

        if(user == null){
            user = new UserModel();
            user.setName(name);
            user.setPassword("bacabaca");
            user.setUsername(username);
            user.setRole(roleService.getRoleByRoleName("Admin"));
            userDb.save(user);
        }


        return jwtUtils.generateJwtToken(loginJwtRequestDTO.getUsername());
    }
}
