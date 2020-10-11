package LokeshRestAssured.Sep2020RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloadData.payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class RahulShett1 {

	//@Test
	public void testPostPUTGetCall() {
		
		RestAssured.baseURI ="https://www.rahulshettyacademy.com/";
		String updAddress = "Balaji Layout, IND";
		
		String response = given().log().all().
			queryParam("key", "qaclick123").
			header("Content-type","application/json").
			body(payload.addPlace()).
		when().
			post("maps/api/place/add/json").
		then().log().all().
			assertThat().statusCode(200).body("scope",equalTo("APP")).extract().asString();
		
		JsonPath jp = new JsonPath(response);  //parsing Response body (String) into json path
		String placeID = jp.getString("place_id");
		System.err.println(placeID);
		
		//Updating Place using PUT call
		
		given().
			log().all().
			queryParam("key", "qaclick123").
			header("Content-type","application/json").
			body(payload.updatePlace(placeID,updAddress)).
		when().
			put("/maps/api/place/update/json").
		then().
			assertThat().log().all().
			statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// Getting place using GET call
		String upResponse = given().
			log().all().
			queryParam("key", "qaclick123").
			queryParam("place_id",placeID).
		when().
			get("/maps/api/place/get/json").
		then().
			assertThat().statusCode(200).
			log().all().extract().response().asString();
		
		JsonPath jp1 = new JsonPath(upResponse);
		String expected = jp1.getString("address");
		Assert.assertEquals(updAddress, expected);
	}
}
