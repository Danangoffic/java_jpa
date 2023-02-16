package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student danangArifRahmanda = new Student(
                    "Danang Arif Rahmanda",
                    "darifrahmanda@gmail.com",
                    LocalDate.of(1995, Month.MAY, 9)
            );

            Student riseldaRahmaAnnisaLalusu = new Student(
                    "Riselda Rahma Annisa Lalusu",
                    "riseldaRahmaAnnisaLalusu.lalusu@gmail.com",
                    LocalDate.of(1996, Month.JULY, 7)
            );
            repository.save(danangArifRahmanda);
            repository.save(riseldaRahmaAnnisaLalusu);
        };
    }
}
