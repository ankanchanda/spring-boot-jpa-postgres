package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student ankan = new Student(
                "ankan",
                LocalDate.now(),
                "ankan@gmail.com"
            );

            Student noname = new Student(
                "noname",
                LocalDate.of(2006, Month.AUGUST, 27),
                "noname@gmail.com"
            );
            studentRepository.saveAll(
                List.of(ankan, noname)
            );
        };
    }
}
