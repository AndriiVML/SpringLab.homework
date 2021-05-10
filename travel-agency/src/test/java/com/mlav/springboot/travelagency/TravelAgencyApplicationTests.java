package com.mlav.springboot.travelagency;

import com.mlav.springboot.travelagency.controller.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Profile("test")
class TravelAgencyApplicationTests {
    @LocalServerPort
    private int port;

    @Value("${app.user.login}")
    private String login;

    @Autowired
    private TestRestTemplate testRestTemplate;

    //
//    @Test
//    void contextLoads() {
//    }
    @Test
    void getUser() {
        System.out.println("\n\n\nget login " + login);
        UserModel userModel = testRestTemplate
                .getForObject("http://localhost:" + port + "api/v1/users/" + login, UserModel.class);
        assertEquals(userModel.getUserDto().getLogin(), login);
    }

}
