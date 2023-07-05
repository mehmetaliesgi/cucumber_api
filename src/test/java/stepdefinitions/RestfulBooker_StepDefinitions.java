package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RestfulBooker_StepDefinitions {
    Response response;
    JsonPath jsonPathResponse;
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
    @Then("Response attribute değerlerinin {string}, {string}, {int}, {string}, {string}, {string} olduğu kontrol edilir")
    public void checkResponseAttributes(String firstname, String lastname, Integer totalprice, String checkin, String checkout, String additionalneeds) {
        jsonPathResponse = response.jsonPath();

        assertEquals(firstname, jsonPathResponse.getString("firstname"));
        assertEquals(lastname, jsonPathResponse.getString("lastname"));
        assertEquals(totalprice,(Integer) jsonPathResponse.get("totalprice"));
        assertEquals(checkin, jsonPathResponse.getString("bookingdates.checkin"));
        assertEquals(checkout, jsonPathResponse.getString("bookingdates.checkout"));
        assertEquals(additionalneeds, jsonPathResponse.getString("additionalneeds"));

    }
}
