package com.mlav.springboot.travelagency;

import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.exception.UserNotFoundException;
import com.mlav.springboot.travelagency.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;

@Slf4j
@SpringBootApplication
public class TravelAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class, args);
    }

    @Bean
    @Transactional
    public CommandLineRunner demoUser(UserService userService,
                                      @Value("${app.user.login}") String login,
                                      @Value("${app.user.password}") String password,
                                      @Value("${app.user.firstName}") String firstName,
                                      @Value("${app.user.lastName}") String lastName,
                                      @Value("${app.user.email}") String email) {
        return args -> {
            UserDto userDto = UserDto.builder()
                    .login(login)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .discount(0)
                    .isBlocked(false)
                    .build();
            log.info("Check if exists: {}", userDto);
            boolean exists = false;
            try {
                userDto = userService.getUserByLogin(login);
                log.info("User: {}", userDto);
                exists = true;
            } catch (UserNotFoundException ex) {
                log.error("User is not found");
            }
            if (!exists) {
                log.info("Creating default user : {}", userDto);
                userService.createUser(userDto);
                log.info("User is created");
            }
        };
    }


}
