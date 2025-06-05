package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

public class BookStoreSteps {
    public static final String BASE_URL = "https://bookstore.toolsqa.com/Account/v1/";
    private Map<String,String> requestBody= new HashMap<>();
    private Response response;

    @Given("{string} and {string} daxil et")

    public void insertTo(String userName, String password) {
        requestBody.put("userName", userName);
        requestBody.put("password", password);
    }
    @And("header {string}, {string} gonderilir")
     public void addHeader (String keyHeader, String keyValue){

    }

    @When("send request to {string} endpoint")
        public void sendRequest (String endpoint) {
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .when()
                .post(BASE_URL + endpoint);
    }

    @Then("user yarandi")
    public void userCreate() {
        response.then().log().all().body("userID", notNullValue());
    }

    @And("status code {int} olmalıdır")
    public void checkStatusCode (int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }
        }



