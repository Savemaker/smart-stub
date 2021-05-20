package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class StubApplication {
    public static void main(String[] args) {
        SpringApplication.run(StubApplication.class, args);
    }
}
