package com.lab6;

import com.lab6.entity.Role;
import com.lab6.entity.User;
import com.lab6.repository.RoleRepository;
import com.lab6.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Lab6Application {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public Lab6Application(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Lab6Application.class, args);
    }

    @PostConstruct
    public void initAdminUser() {
        var adminUser = userRepository.findByUsername("admin");
        if (adminUser.isEmpty()) {
            List<Role> listAdminRoles = new ArrayList<>();
            var adminRole = roleRepository.findByName("ROLE_ADMIN");
            if (adminRole.isEmpty()) {
                var newAdminRole = new Role(null, "ROLE_ADMIN");
                listAdminRoles.add(newAdminRole);
            } else {
                listAdminRoles.add(adminRole.get());
            }
            User newAdminUser = new User(null, "Admin", "Admin", "Admin", "admin",
                    passwordEncoder.encode("test1234"), "admin@lab.com",
                    true, true, true, true);
            newAdminUser.setRoles(listAdminRoles);
            userRepository.save(newAdminUser);
        }
    }

}
