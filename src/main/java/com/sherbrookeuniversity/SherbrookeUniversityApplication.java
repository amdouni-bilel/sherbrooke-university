package com.sherbrookeuniversity;

import com.sherbrookeuniversity.entity.Admin;
import com.sherbrookeuniversity.entity.Role;
import com.sherbrookeuniversity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SherbrookeUniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SherbrookeUniversityApplication.class, args);
    }

    @Bean
    public CommandLineRunner initAdmin(@Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("youssef.kallel@gmail.com").isEmpty()) {
                Admin admin = new Admin();
                admin.setFirstName("Youssef");
                admin.setLastName("Kallel");
                admin.setEmail("youssef.kallel@gmail.com");
                admin.setPassword(passwordEncoder.encode("12345"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Admin account created: youssef.kallel@gmail.com");
            }
        };
    }
}
