package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static java.lang.Math.log;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class BookStore {
    public static final String BASE_URL = "https://bookstore.toolsqa.com/Account/v1/";
    String token = "";

    @Test
    public void createAccount() {

        String jsonBody = "{\n" +
                "  \"userName\": \"Aytac123\",\n" +
                "  \"password\": \"Qwerty@1235\"\n" +
                "}";

        RestAssured
                .given()
                    .header("Content-Type", "application/json")
                    .body(jsonBody)
                .when()
                    .post(BASE_URL + "User")
                .then()
                    .statusCode(201).log().all();

    }

    @Test
    public void negativeCaseCreateAccount() {
        String jsonBody = "{\n" +
                "  \"userName\": \"Asef\",\n" +
                "  \"password\": \"Qwerty125\"\n" +
                "}";

        RestAssured
                .given()
                    .header("Content-Type", "application/json")
                    .body(jsonBody)
                .when()
                    .post(BASE_URL + "User")
                .then()
                    .statusCode(400).log().all();

    }


    @Test
    public void getToken() {
        String jsonBody = "{\n" +
                "  \"userName\": \"Aytac123\",\n" +
                "  \"password\": \"Qwerty@1235\"\n" +
                "}";

        Response rawResponse = RestAssured
                .given()
                    .header("Content-Type", "application/json")
                    .body(jsonBody)
                .when()
                    .post(BASE_URL + "GenerateToken");

                rawResponse
                .then()
                    .statusCode(200).log().all()
                    .body("token", notNullValue())
                    .body("status", equalTo("Success"))
                    .body("result", equalTo("User authorized successfully."));
                    token = rawResponse.jsonPath().getString("token");
                    System.out.println("token : " + token);
    }

    @Test
    public void getAllBooks() {
        RestAssured
                .given()
                        .header("Content-Type", "application/json")
                .when()
                        .get("https://bookstore.toolsqa.com/BookStore/v1/Books")
                .then()
                        .statusCode(200).log().all();
    }

    @Test
    public void getBookByIsbn() {
        String ISBN = "9781449325862";
       RestAssured
                .given()
                .log().all()
                        .header("Content-Type", "application/json").queryParam("ISBN", "9781449325862")
                .when()
                        .get("https://bookstore.toolsqa.com/BookStore/v1/Book")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void createBook() {

        String jsonBody = "{\n" +
                "  \"userName\": \"Aytac123\",\n" +
                "  \"password\": \"Qwerty@1235\"\n" +
                "}";

        RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(BASE_URL + "User")
                .then()
                .statusCode(201).log().all();

    }
}
