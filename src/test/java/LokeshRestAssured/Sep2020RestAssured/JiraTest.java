package LokeshRestAssured.Sep2020RestAssured;

import org.junit.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import payloadData.payload;

public class JiraTest {
	
	static String jiraSessionID;
	static String baseurl ="http://192.168.1.8";
	static int portNum = 8080;
	
	//@BeforeTest
	public void getAuthentication() {
		RestAssured.baseURI = baseurl;
		RestAssured.port = portNum;
		String resource = "/rest/auth/1/session";
		
		String response = given().
			header("Content-Type","application/json").
			body(payload.jiraLoginBody("lokesh.kondepudi","test@123")).
		when().
			log().all().
			post(resource).
		then().
			log().all().extract().response().asString();
		
		System.err.println(response);
		JsonPath jp = new JsonPath(response);
		jiraSessionID = jp.get("session.value");
		System.err.println(jiraSessionID);
	}
	
	
	//@Test
	public void createJiraTicket() throws IOException {

		RestAssured.baseURI = baseurl;
		RestAssured.port = portNum;
		String resource = "/rest/api/2/issue";
		
		String response = given().
				header("Content-Type","application/json").
				header("Cookie",jiraSessionID).
				body(new String(Files.readAllBytes(Paths.get("C:\\Users\\lokes\\eclipse-workspace\\Sep2020RestAssured\\src\\test\\java\\payloadData\\jiraAddTicket.json")))).
			when().
				log().all().
				post(resource).
			then().
				log().all().extract().response().asString();
		System.err.println(response);
	}
	
	//@Test
	public void addJiraComment() {
		RestAssured.baseURI = baseurl;
		RestAssured.port = portNum;
		SessionFilter session = new SessionFilter();
		String response = 
				given().
					header("Content-Type","application/json").
					body(payload.jiraLoginBody("lokesh.kondepudi","test@123")).
					log().all().
					filter(session).
				when().
					post("/rest/auth/1/session").
				then().
					extract().asString();
		
		given().pathParam("key", "10000").header("Content-Type","application/json").body("{\r\n" + 
				"   \"body\":\"This is my first comment from rest assure api\",\r\n" + 
				"   \"visibility\":{\r\n" + 
				"      \"type\":\"role\",\r\n" + 
				"      \"value\":\"Administrators\"\r\n" + 
				"   }\r\n" + 
				"}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201);
					
		System.err.println(response);
	}

}
