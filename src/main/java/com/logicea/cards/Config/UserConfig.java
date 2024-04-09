package com.logicea.cards.Config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.logicea.cards.Models.Role;
import com.logicea.cards.Models.User;
import com.logicea.cards.Repositories.UserRepository;

@Configuration
public class UserConfig {

    private final PasswordEncoder passwordEncoder;

    public UserConfig(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner commandUserLineRunner(UserRepository repository) {
        return args -> {
            User adminUser = new User(
                    null,
                    "eric",
                    "test-admin",
                    "admin@logicea.co.ke",
                    passwordEncoder.encode("123456"),
                    Role.Admin, null, null, 0, null);

            User memberUser = new User(
                    null,
                    "tony",
                    "test2",
                    "member2@logicea.co.ke",
                    passwordEncoder.encode("123456"),
                    Role.Member, null, null, 1, null);

            User memberUser2 = new User(
                    null,
                    "tony",
                    "test",
                    "member@logicea.co.ke",
                    passwordEncoder.encode("123456"),
                    Role.Member, null, null, 2, null);

            repository.saveAll(List.of(adminUser, memberUser, memberUser2));
        };
    }
}
