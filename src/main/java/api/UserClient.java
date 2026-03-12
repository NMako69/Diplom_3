package api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import models.CreateUser;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String REGISTER = "/api/auth/register";
    private static final String DELETE = "/api/auth/user";

    @Step("Создание пользователя через API")
    public Response createUser(CreateUser user) {

        return given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .body(user)
                .post(REGISTER);
    }

    @Step("Удаление пользователя через API")
    public Response deleteUser(String token) {

        return given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .delete(DELETE);
    }
}
