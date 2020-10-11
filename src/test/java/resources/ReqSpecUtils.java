package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReqSpecUtils {

	public static RequestSpecification reqSpec;
	PrintStream log;
	
	public RequestSpecification requestSpec() throws IOException {
		if(reqSpec == null) {
		log = new PrintStream(new FileOutputStream("log.txt"));
		return reqSpec = new RequestSpecBuilder().
							setBaseUri(getProperties("baseUrl")).
							addQueryParam("key", "qaclick123").
							addFilter(RequestLoggingFilter.logRequestTo(log)).
							addFilter(ResponseLoggingFilter.logResponseTo(log)).
							setContentType(ContentType.JSON).build();
		}
		return reqSpec;	}
	
	
	public String getProperties(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\test\\java\\resources\\config.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	
	public String getJsonPath(Response response, String key) {
		
		String resp = response.asString();
		JsonPath jp = new JsonPath(resp);
		return jp.get(key).toString();
	}
}
