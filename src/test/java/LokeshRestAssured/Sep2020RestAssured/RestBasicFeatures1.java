package LokeshRestAssured.Sep2020RestAssured;

import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class RestBasicFeatures1 {
	
	/**
	 *  Basic Status checking..
	 */
	
	//@Test
	public void testStatusCode() {

		RestAssured.
			given().
				get("https://reqres.in/api/users/2").
			then().
				statusCode(200).log().all();
	}
	
	/**
	 *  Testing equalTo method using hamcrest matchers
	 */
	
	//@Test
	public void testEqualFunction() {
		ValidatableResponse res = RestAssured.
			given().
				get("https://reqres.in/api/users/").
			then().
				body("data[0].first_name", equalTo("George"));
		System.err.println(res);

	}
	
	/**
	 *  Testing hasItems function using hamcrest matchers
	 */
	
	//@Test
	public void testHasItems() {
		RestAssured.
				given().
					get("https://reqres.in/api/users/").
				then().
					body("data.first_name", hasItems("George","Emma","Tracey"));
	}

	/**
	 *  Testing parameters and headers
	 */
	//@Test
	public void testParametersHeaders() {
		
		RestAssured.
			given().
				param("test","rest").
				header("Server", "cloudflare").
			when().
				get("https://reqres.in/api/users?page=2").
			then().
				statusCode(200).log().all();
	}
	
	/**
	 * Test All Parameters without root
	 */
	
	//@Test
	public void testWithoutRoot() {
		RestAssured.
			given().
				get("https://reqres.in/api/users?page=2").
			then().
				body("data[0].email", is("michael.lawson@reqres.in"));
	}
	
	/**
	 * Test All Parameters with root
	 */
	
	//@Test
	public void testWithRoot() {
		RestAssured.
			given().
				get("https://reqres.in/api/users?id=7").
			then().
			root("data").
				body("email", is("michael.lawson@reqres.in")).and().
				body("first_name", is("Michael"));
	}
	
	/**
	 *  To get all response as a String
	 */
	//@Test
	public void testGetResponseasString() {
		String responseString = RestAssured.get("https://reqres.in/api/unknown").asString();
		System.out.println(responseString);
	}
	
	/**
	 *  To get all response as input stream
	 */
	//@Test
	public void testGeteResponsasInputStream() {
		InputStream resInputStream = RestAssured.get("https://reqres.in/api/unknown").asInputStream();
		System.err.println(resInputStream.toString().length());
	}
	
	/**
	 *  Serialization in RestAssured
	 */
	//@Test
	public void testSerialization() {

		HashMap<String, Object> payLoad = new HashMap<String, Object>();
		payLoad.put("id",102);
		payLoad.put("firstName","Lokesh");
		payLoad.put("lastName","Kondepudi");
		payLoad.put("email","kplokesh@ymail.com");
		payLoad.put("programme","Computers");
		//payLoad.put("courses","Selenium");
		
		
		String res = RestAssured.
			given().
				contentType("application/json").
				body(payLoad).
			when().
				post("http://localhost:8080/student/").andReturn().asString();		
		System.err.println(res);
	}
	
	
	/**
	 * Serialization can also be done using POJO class
	 */
	//@Test
	public void testSerializationWithPojo() {
		List<String> course = new ArrayList<String>();
		course.add("Algebra");
		course.add("Calculus");
		course.add("Geometry");
				
		StudentPojo sp = new StudentPojo();
		sp.setId(103);
		sp.setFirstName("SriRam");
		sp.setLastName("Ramayanam");
		sp.setEmail("rama@gmail.com");
		sp.setProgramme("Maths");
		sp.setCourses(course);
		
		RestAssured.
			given().
				contentType("application/json").
				body(sp).
			when().
				post("http://localhost:8080/student/").
			then().
				statusCode(201);
	}
	
	
	/**
	 * Serialization can also be done using explicit Serializer (converting java object into json using jackson 2)
	 */
	//@Test
	public void testSerializationWithExplicit() {
		List<String> course = new ArrayList<String>();
		course.add("Algebra1");
		course.add("Calculus1");
		course.add("Geometry1");
				
		StudentPojo sp = new StudentPojo();
		sp.setId(104);
		sp.setFirstName("Sairama");
		sp.setLastName("Ramayanam1");
		sp.setEmail("rama1@gmail.com");
		sp.setProgramme("Maths1");
		sp.setCourses(course);
		
		RestAssured.
			given().
				contentType("application/json").
				body(sp,ObjectMapperType.JACKSON_2).
			when().
				post("http://localhost:8080/student/").
			then().
				statusCode(201);
	}
	
	//@Test
	public void testDSerialization() {
				
		StudentPojo sp = new StudentPojo();
		sp.setId(104);
		sp.setFirstName("Sairama");
		sp.setLastName("Ramayanam");
		sp.setEmail("rama@gmail.com");
		sp.setProgramme("Maths");
		
		StudentResPojo sp1 = RestAssured.
				given().
					body(sp).
				when().
					post("http://localhost:8080/student/").
					as(StudentResPojo.class);
		System.out.println(sp1.toString());
	}
}
