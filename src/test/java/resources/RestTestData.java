package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;

public class RestTestData {

	public AddPlace addPlacePayLoad(String name, String language, String addresss) {
	
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(addresss);
		ap.setLanguage(language);
		ap.setPhone_number("0884-2396411");
		ap.setWebsite("https://rahulshettyacademy.com");
		ap.setName(name);
		List<String> myList = new ArrayList<String>();
		myList.add("Aqua Park");
		myList.add("Culture");
		ap.setTypes(myList);
		Location loc = new Location();
		loc.setLat(-65.3434);
		loc.setLng(-67.3453);
		ap.setLocation(loc);
		
		return ap;
	}
	
	public String deletePlacePayLoad(String placeId) {
		return  "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
