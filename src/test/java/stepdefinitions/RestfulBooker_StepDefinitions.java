package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RestfulBooker_StepDefinitions {
    Response response;
    JsonPath jsonPathResponse;
    JSONObject requestBody;
    JSONObject bookigDatesObject;
    JSONObject authBody;
    String token;
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
    @Given("Request için gerekli veriler girilir ve body oluşturulur, {string}, {string}, {int}, {string}, {string}, {string}, {string}")
    public void createRequestBody(String firstname, String lastname, Integer totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        requestBody = new JSONObject();
        bookigDatesObject = new JSONObject();

        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("totalprice", totalprice);
        requestBody.put("depositpaid", Boolean.parseBoolean(depositpaid));
        bookigDatesObject.put("checkin", checkin);
        bookigDatesObject.put("checkout", checkout);
        requestBody.put("bookingdates", bookigDatesObject);
        requestBody.put("additionalneeds", additionalneeds);
    }
    @When("POST requesti yapılır ve response değeri kaydedilir")
    public void doPostRequestAndSaveResponse() {
        response = given().contentType(ContentType.JSON).when().body(requestBody.toString()).post(jphStepdefinitions.endpoint);
        response.prettyPrint();
    }
    @Given("Kullanıcı bir token üretir")
    public void createToken() {
        String endpoint = "https://restful-booker.herokuapp.com/auth";
        authBody = new JSONObject();
        authBody.put("username", "admin");
        authBody.put("password", "password123");

        response = given().contentType(ContentType.JSON).when().body(authBody.toString()).post(endpoint);
        jsonPathResponse = response.jsonPath();
        token = jsonPathResponse.getString("token");
        System.out.println(token);
    }
    @When("PUT requesti yapılır ve response değeri kaydedilir")
    public void doPutRequestAndSaveResponse() {
        response = given().contentType(ContentType.JSON).auth().preemptive().basic("admin", "password123").when().body(requestBody.toString()).put(jphStepdefinitions.endpoint);
    }

}
