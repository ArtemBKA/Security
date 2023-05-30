package com.example.security.init;

import com.example.security.service.RoleService;
import com.example.security.service.UserService;
import org.springframework.stereotype.Component;
import com.example.security.model.Role;
import com.example.security.model.User;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitClass {
    private final RoleService roleService;
    private final UserService userService;

    public InitClass(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        userRoles.add(roleUser);

        User userAdmin = new User("admin", "admin", 100, "admin@mail.ru",  "admin", adminRoles);
        User userUser = new User("user", "user", 100, "user@mail.ru",  "user", userRoles);
        System.out.println(userAdmin);
        userService.createNewUser(userAdmin);
        System.out.println(userUser);
        userService.createNewUser(userUser);
    }
}
