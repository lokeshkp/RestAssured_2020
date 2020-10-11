package stepdefs;

import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.ReqSpecUtils;
import resources.RestTestData;

public class AddPlaceTest extends ReqSpecUtils {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	static String place_id;
	RestTestData testData = new RestTestData();


	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String addresss) throws IOException {
		reqSpec = given().spec(requestSpec()).body(testData.addPlacePayLoad(name,language,addresss));	
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource,String method) {
		APIResources resApi = APIResources.valueOf(resource);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) {
			response = reqSpec.when().post(resApi.getResource());
		}else if(method.equalsIgnoreCase("GET")) {
			response = reqSpec.when().get(resApi.getResource());
		}else if(method.equalsIgnoreCase("DELETE")) {
			response = reqSpec.when().delete(resApi.getResource());
		}
	}
	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String val) {
		String resp = response.asString();
		assertEquals(getJsonPath(response, key), val);
	}

	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expName, String resource) throws IOException {
		place_id = getJsonPath(response, "place_id");
		reqSpec = given().spec(requestSpec()).queryParam("place_id",place_id);
		user_calls_with_post_http_request(resource,"GET");
		String name = getJsonPath(response, "name");
		assertEquals(name, expName);
	}


	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		reqSpec = given().spec(requestSpec().body(testData.deletePlacePayLoad(place_id)));
	}




}
