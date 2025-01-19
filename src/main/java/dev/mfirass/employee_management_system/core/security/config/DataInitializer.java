package dev.mfirass.employee_management_system.core.security.config;
import dev.mfirass.employee_management_system.core.security.model.Role;
import dev.mfirass.employee_management_system.core.security.model.User;
import dev.mfirass.employee_management_system.core.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {


            User hrPersonnel = new User();
            hrPersonnel.setEmail("hr.personnel@example.com");
            hrPersonnel.setPassword(passwordEncoder.encode("hrpersonnel123"));
            hrPersonnel.setRole(Role.HR_PERSONNEL);
            userRepository.save(hrPersonnel);


            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMINISTRATOR);
            userRepository.save(admin);



            User manager = new User();
            manager.setEmail("manager@example.com");
            manager.setPassword(passwordEncoder.encode("manager123"));
            manager.setRole(Role.MANAGER);
            userRepository.save(manager);
        };
    }
}