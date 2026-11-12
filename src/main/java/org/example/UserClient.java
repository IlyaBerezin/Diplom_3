package org.example;

import static io.restassured.RestAssured.given;
import static org.example.constants.Constants.*;
import io.restassured.response.Response;

public class UserClient {

    public Response create(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(AUTH_URL);
    }

    public Response login(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(LOGIN_URL);
    }

    public Response delete(String token) {
        return given()
                .header("Authorization", token)
                .delete(DELETE_USER_URL);
    }
}
