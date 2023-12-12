package apap.tutorial.bacabaca.service;

import apap.tutorial.bacabaca.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllList();
    Role getRoleByRoleName(String name);
}
