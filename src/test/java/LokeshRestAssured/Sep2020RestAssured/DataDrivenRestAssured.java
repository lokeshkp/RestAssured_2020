package LokeshRestAssured.Sep2020RestAssured;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payloadData.payload;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class DataDrivenRestAssured {
	
	
	@Test
	public void addBook() {
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
	
		String response = given().
			header("Content-Type","application/json").
			body(payload.addBook("sairam","555")).
		when().log().all().
			post("/Library/Addbook.php").
		then().log().all().extract().response().asString();
		
		System.err.println(response);
		JsonPath jp = new JsonPath(response);
		String bookId = jp.get("ID");
		System.err.println(bookId);
		
	}

}
