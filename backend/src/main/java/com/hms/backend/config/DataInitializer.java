package com.hms.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hms.backend.entity.Role;
import com.hms.backend.entity.RoleName;
import com.hms.backend.repository.RoleRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {

        return args -> {

            for (RoleName roleName : RoleName.values()) {

                if (roleRepository.findByName(roleName).isEmpty()) {

                    roleRepository.save(new Role(roleName));

                }

            }

        };
        
    }

}