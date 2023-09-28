package ru.kartel.conditionalapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalAppApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    private static final GenericContainer<?> myDevApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> myProdApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);


    @BeforeAll
    public static void setUp() {
        myDevApp.start();
        myProdApp.start();
    }

    @Test
    void testMyDev() {
        String dev = "Current profile is dev";
        ResponseEntity<String> entityFromDev = restTemplate.getForEntity("http://localhost:" + myDevApp.getMappedPort(8080), String.class);
        Assertions.assertEquals(dev, entityFromDev.getBody());
    }

    @Test
    void testMyProd() {
        String prod = "Current profile is production";
        ResponseEntity<String> entityFromDev = restTemplate.getForEntity("http://localhost:" + myProdApp.getMappedPort(8081), String.class);
        Assertions.assertEquals(prod, entityFromDev.getBody());
    }

}
