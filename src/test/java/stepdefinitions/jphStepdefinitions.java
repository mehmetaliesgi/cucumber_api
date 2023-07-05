package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class jphStepdefinitions {
    static String endpoint;
    Response response;
    JsonPath responseJsonPath;
    JSONObject requestBody;

    @Given("Kullanici {string} base URL'ini kullanir")
    public void kullanici_base_url_ini_kullanir(String baseUrl) {
        endpoint = ConfigReader.getProperty(baseUrl);
    }
    @Then("Path parametreleri icin {string} kullanir")
    public void path_parametreleri_icin_kullanir(String pathParams) {
        endpoint = endpoint + "/" + pathParams;
    }
    @Then("jPH server a GET request gonderir ve testleri yapmak icin response degerini kaydeder")
    public void j_ph_server_a_get_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {
        response = given().when().get(endpoint);
    }
    @Then("jPH respons'da status degerinin {int}")
    public void j_ph_respons_da_status_degerinin(Integer statusCode) {
        Assert.assertEquals(statusCode, (Integer) response.statusCode());
    }
    @Then("jPH respons'da content type degerinin {string}")
    public void j_ph_respons_da_content_type_degerinin(String contentType) {
        Assert.assertEquals(contentType, response.contentType());
    }
    @Then("jPH GET respons body'sinde {string} degerinin Integer {int}")
    public void j_ph_get_respons_body_sinde_degerinin_ınteger(String attribute, Integer expectedValue) {
        responseJsonPath = response.jsonPath();
        Assert.assertEquals(expectedValue,(Integer) responseJsonPath.getInt(attribute));
    }
    @Then("jPH GET respons body'sinde {string} degerinin String {string}")
    public void j_ph_get_respons_body_sinde_degerinin_string(String attribute, String expectedValue) {
        responseJsonPath = response.jsonPath();
        Assert.assertEquals(expectedValue, responseJsonPath.getString(attribute));
    }
    @And("Post request icin {string}, {string}, {int}, {int} bilgileri ile request body olusturulur")
    public void request_body_olustur(String title, String body, int userId, int id) {
        requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("body", body);
        requestBody.put("userId", userId);
        requestBody.put("id", id);
    }
    @And("jPH server'a POST request gonderilir ve testleri yapmak icin response degerini kaydeder")
    public void request_body_gonder() {
        response = given().contentType(ContentType.JSON).when().body(requestBody.toString()).post(endpoint);
        response.prettyPrint();
    }
    @Then("jPH response'daki {string} header degerinin {string} oldugunu test et")
    public void response_header_test(String attribute, String expectedData) {
        Assert.assertEquals(expectedData, response.header(attribute));
    }
    @Then("response attributelerini degerlerinin {string},{string},{int},{int} olduğunu kontrol et")
    public void all_response_values_test(String title, String body, int userId, int id) {
        responseJsonPath = response.jsonPath();
        Assert.assertEquals(title, responseJsonPath.getString("title"));
        Assert.assertEquals(body, responseJsonPath.getString("body"));
        Assert.assertEquals(userId, responseJsonPath.getInt("userId"));
        Assert.assertEquals(id, responseJsonPath.getInt("id"));
    }
}
