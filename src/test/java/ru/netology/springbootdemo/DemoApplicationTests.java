package ru.netology.springbootdemo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @Container
    private static final GenericContainer<?> firsMyApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> secondMyApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

//    @BeforeAll
//    public static void setUp() {
//
//    }

    @Test
    void contextLoads() {
        Integer firstAppPort = firsMyApp.getMappedPort(8080);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + firstAppPort + "/profile", String.class);


        Integer secondAppPort = secondMyApp.getMappedPort(8081);
        ResponseEntity<String> forSecondEntity = restTemplate.getForEntity("http://localhost:" + secondAppPort + "/profile", String.class);


        Assertions.assertEquals(forEntity.getBody(), "Current profile is dev");
        Assertions.assertEquals(forSecondEntity.getBody(), "Current profile is production");


    }

}
