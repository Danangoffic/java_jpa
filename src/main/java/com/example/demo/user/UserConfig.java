package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner2(UserRepository userRepository){
        return args -> {
            User danangArif = new User("Danang Arif", "darifrahmanda@gmail.com", "123123");
            User riseldaLalusu = new User("Riselda Lalusu", "risel.lalusu@gmail.com", "123123");

            userRepository.save(danangArif);
            userRepository.save(riseldaLalusu);
        };
    }
}
