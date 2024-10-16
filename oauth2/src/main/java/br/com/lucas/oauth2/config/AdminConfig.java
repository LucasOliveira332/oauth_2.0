package br.com.lucas.oauth2.config;

import br.com.lucas.oauth2.models.User;
import br.com.lucas.oauth2.enums.Role;
import br.com.lucas.oauth2.repositories.RoleRepository;
import br.com.lucas.oauth2.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(Role.ADMIN.name());

        var admin = userRepository.findByUsername("admin");

        admin.ifPresentOrElse(
                (user) -> {
                    System.out.println("admin já existe");
                },
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("123"));
                    user.setRole(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );
    }
}
