package Deserilization;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DesrilizationTest {
	
	//@Test
	public static void main(String[] args) {
		
		GetCountries gc = RestAssured.get("C:\\Users\\lokes\\eclipse-workspace\\Sep2020RestAssured\\src\\test\\java\\Deserilization\\countriesResponse.json").as(GetCountries.class);
		
		System.out.println(gc.getInstructor());
	}

}
