package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String REGISTER = "/api/auth/register";
    private static final String DELETE = "/api/auth/user";

    public UserClient() {
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }

    @Step("Создание пользователя через API")
    public Response createUser(String email, String password, String name) {
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}", email, password, name);

        return given()
                .header("Content-type", "application/json")
                .body(body)
                .post(REGISTER);
    }

    @Step("Удаление пользователя по токену через API")
    public void deleteUser(String token) {
        given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .delete(DELETE)
                .then()
                .statusCode(202); // подтверждаем успешное удаление
    }
}
