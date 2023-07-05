package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RestfulBooker_StepDefinitions {
    Response response;

    @Given("GET requesti yapılır ve response degerini kaydeder")
    public void doRequestAndSaveResponse() {
        response = given().when().get(jphStepdefinitions.endpoint);
        response.prettyPrint();
    }
    @Then("Request sonucu response'da status degerinin {int} olduğu kontrol edilir")
    public void checkStatusCode(Integer statusCode) {
        assertEquals(statusCode, (Integer) response.statusCode());
    }
    @Then("Request sonucu response'da content type degerinin {string}")
    public void checkContentType(String contentType) {

    }
}
