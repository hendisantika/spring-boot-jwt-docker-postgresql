package com.hendisantika.springbootjwtdockerpostgresql.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-docker-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/23
 * Time: 21:58
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class AuthControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = this.port;
    }

    @Test
    @Order(1)
    @DisplayName("Login with a wrong credentials")
    public void testWrongLoginPassword() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"phoneNumber\":\"123\", \"password\":\"321\"}")
                .log().all()
                .when()
                .post("/api/auth/signin")
                .then()
                .statusCode(401).log().all()
                .body("message", equalTo("Bad credentials"))
                .body("status", equalTo(401));
    }

    @Test
    @Order(2)
    @DisplayName("Register user")
    public void testRegister() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"phoneNumber\":\"081655242331\", \"password\":\"Password1\", \"name\":\"edwin\"}")
                .log().all()
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(200).log().all()
                .body("message", equalTo("User registered successfully!"));
    }

    @Test
    @Order(2)
    @DisplayName("Login with a right credentials")
    public void testSuccessLogin() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"phoneNumber\":\"081655242331\", \"password\":\"Password1\"}")
                .log().all()
                .when()
                .post("/api/auth/signin")
                .then()
                .statusCode(200).log().all()
                .body("type", equalTo("Bearer"))
                .body("token", is(notNullValue()))
                .body("phoneNumber", equalTo("081655242331"));
    }
}
