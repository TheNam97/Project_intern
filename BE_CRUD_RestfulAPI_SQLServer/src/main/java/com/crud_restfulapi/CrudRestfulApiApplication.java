package com.crud_restfulapi;

import com.crud_restfulapi.model.Role;
import com.crud_restfulapi.model.User;
import com.crud_restfulapi.securityy.service.IRoleService;
import com.crud_restfulapi.securityy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CrudRestfulApiApplication {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService iRoleService;

    @PostConstruct
    public void init() {
        List<User> users = userService.findAll();

        if (users.isEmpty()) {
            User admin = new User();
            Set<Role> roles = new HashSet<>();
            Role roleAdmin = new Role();
            Role roleUser = new Role();

            roleAdmin.setName("ROLE_ADMIN");
            roleUser.setName("ROLE_USER");
            roles.add(roleAdmin);
            admin.setFullName("thenam");
            admin.setUsername("admin");
            admin.setPassword("123");
            admin.setRoles(roles);

            iRoleService.save(roleUser);
            userService.save(admin);
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(CrudRestfulApiApplication.class, args);
    }

}
