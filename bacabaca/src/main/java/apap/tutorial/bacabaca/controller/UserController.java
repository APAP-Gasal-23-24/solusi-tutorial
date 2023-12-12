package apap.tutorial.bacabaca.controller;

import apap.tutorial.bacabaca.dto.UserMapper;
import apap.tutorial.bacabaca.dto.request.CreateUserRequestDTO;
import apap.tutorial.bacabaca.model.UserModel;
import apap.tutorial.bacabaca.service.RoleService;
import apap.tutorial.bacabaca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/add")
    private String addUserForm(Model model){
        UserModel user = new UserModel();
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO();
        model.addAttribute("userDTO", createUserRequestDTO);
        model.addAttribute("listRole", roleService.getAllList());
        model.addAttribute("user", user);
        return "form-create-user";
    }

    @PostMapping("/user/add")
    private String addUserSubmit(@ModelAttribute CreateUserRequestDTO createUserRequestDTO, Model model){
        UserModel userModel = userMapper.createUserRequestDTOToUserModel(createUserRequestDTO);
        userService.addUser(userModel, createUserRequestDTO);
        return "home";
    }
}
