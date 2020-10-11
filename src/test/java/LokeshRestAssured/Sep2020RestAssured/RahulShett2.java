package LokeshRestAssured.Sep2020RestAssured;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payloadData.payload;

public class RahulShett2 {

	
	//@Test
	public void testComplexJsonParse() {
		JsonPath js = new JsonPath(payload.coursePrice());
		int count = js.getInt("courses.size()");
		System.err.println(js.getString("courses[0].title"));
		System.err.println(count);
		
		
		// print all courses titles and prices
		for(int i=0;i<count;i++) {
			int price;
			String course;
			
			course = js.get("courses["+i+"].title");
			price  = js.get("courses["+i+"].price");	
			System.out.println(course);
			System.out.println(price);

		}
		
		// printing only specific course price
		for(int i=0;i<count;i++) {
			int price;
			String course;
			
			course = js.get("courses["+i+"].title");
			price  = js.get("courses["+i+"].price");	
			if(course.equalsIgnoreCase("RPA")) {
				System.err.println("RPA Price Tag-"+price);	
				break;
			}
		}
	}
	
	// Reading json file and converting to Byte data and then converting byte data into String and finally feeding as a payload
	
	//@Test
	public void testReadJsonPayload() throws IOException {
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		
		String response = given().
			header("Content-Type","application/json").
			body(new String(Files.readAllBytes(Paths.get("C:\\Users\\lokes\\eclipse-workspace\\Sep2020RestAssured\\src\\test\\java\\payloadData\\addPlace.json")))).
		when().log().all().
			post("/Library/Addbook.php").
		then().log().all().extract().response().asString();
	}
}
