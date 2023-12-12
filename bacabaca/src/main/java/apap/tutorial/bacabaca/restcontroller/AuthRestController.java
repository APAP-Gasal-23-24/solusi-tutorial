package apap.tutorial.bacabaca.restcontroller;

import apap.tutorial.bacabaca.dto.UserMapper;
import apap.tutorial.bacabaca.dto.request.JwtRequestFlutterDTO;
import apap.tutorial.bacabaca.dto.request.LoginJwtRequestDTO;
import apap.tutorial.bacabaca.dto.response.LoginJwtResponseDTO;
import apap.tutorial.bacabaca.security.jwt.JwtUtils;
import apap.tutorial.bacabaca.service.RoleService;
import apap.tutorial.bacabaca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class AuthRestController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    // Api for web admin used to add sso user to db and generate jwt token
    @PostMapping("/auth/login-jwt-webadmin")
    public ResponseEntity<?> loginJwtAdmin(@RequestBody LoginJwtRequestDTO loginJwtRequestDTO) {
        try{
            String jwtToken = userService.loginJwtAdmin(loginJwtRequestDTO);
            return new ResponseEntity<>(new LoginJwtResponseDTO(jwtToken), HttpStatus.OK);
        }catch (SecurityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // API for flutter to authenticate user credentials and generate jwt token
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginJwtFlutter(@RequestBody JwtRequestFlutterDTO jwtRequestFlutterDTO) {
        try{
            authenticate(jwtRequestFlutterDTO.getUsername(), jwtRequestFlutterDTO.getPassword());
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        final String token = jwtUtils.generateJwtToken(jwtRequestFlutterDTO.getUsername());
        return ResponseEntity.ok(new LoginJwtResponseDTO(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(encoder);
        AuthenticationManager authenticationManager = new ProviderManager(authenticationProvider);

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        } catch (Exception e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
