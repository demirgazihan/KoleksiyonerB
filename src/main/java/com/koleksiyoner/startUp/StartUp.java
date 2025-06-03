package com.koleksiyoner.startUp;

import com.koleksiyoner.entities.Role;
import com.koleksiyoner.entities.User;
import com.koleksiyoner.enums.ERole;
import com.koleksiyoner.enums.EStatus;
import com.koleksiyoner.repositories.RoleRepository;
import com.koleksiyoner.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StartUp implements CommandLineRunner {


    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        saveRoles();
        saveUsers();
    }

    public void saveUsers() {
        if (!userRepository.existsByEmail("admin@gmail.com")) {
            User user = new User();
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
            user.setEmail("admin@gmail.com");
            user.setFirstname("admin");
            user.setLastname("admin");
            user.setUsername("admin");
            user.setPassword(encoder.encode("admin"));
            user.setRoles(roles);
            user.setPhone("11111111111");
            user.setEStatus(EStatus.ACTIVE);
            userRepository.save(user);
        }
    }

    public void saveRoles() {
        Optional<Role> optRoleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN);
        if (optRoleAdmin.isEmpty()) {
            Role role = new Role();
            role.setName(ERole.ROLE_ADMIN);
            roleRepository.save(role);
        }

        Optional<Role> optRoleOwn = roleRepository.findByName(ERole.ROLE_MODERATOR);
        if (optRoleOwn.isEmpty()) {
            Role role = new Role();
            role.setName(ERole.ROLE_MODERATOR);
            roleRepository.save(role);
        }

        Optional<Role> optRoleMe = roleRepository.findByName(ERole.ROLE_USER);
        if (optRoleMe.isEmpty()) {
            Role role = new Role();
            role.setName(ERole.ROLE_USER);
            roleRepository.save(role);
        }
    }
}
