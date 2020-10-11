package payloadData;

public class payload {

	public static String addPlace() {
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"https://www.rahulshettyacademy.com/\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}";
	}
	
	public static String updatePlace(String placeID, String upaddress) {
		return "{\r\n" + 
				"\"place_id\":\""+placeID+"\",\r\n" + 
				"\"address\":\""+upaddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}";
	}
	
	public static String coursePrice() {
		return  "{\r\n" + 
				" \"dashboard\":{\r\n" + 
				"\"purchaseAmount\":910,\r\n" + 
				"\"website\":\"rahulsettyacademy.com\",\r\n" + 
				"},\r\n" + 
				"\"courses\":[\r\n" + 
				"{\r\n" + 
				"\"title\":\"Selenium Java\",\r\n" + 
				"\"price\":50,\r\n" + 
				"\"copies\":6\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\"title\":\"Cypress\",\r\n" + 
				"\"price\":40,\r\n" + 
				"\"copies\":4\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\"title\":\"RPA\",\r\n" + 
				"\"price\":45,\r\n" + 
				"\"copies\":10\r\n" + 
				"}\r\n" + 
				"]\r\n" + 
				"}";
	}
	
	public static String addBook(String isbn, String aisle) {
		return "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Cypress Automation with JavaScript\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"Om Sairam\"\r\n" + 
				"}";
	}
	
	public static String jiraLoginBody(String username, String pwd) {
		return  "{ \"username\": \""+username+"\", \"password\": \""+pwd+"\" }";
	}
	
	public static String jiraAddTicketBody(String summary, String description, String issueType) {
		return  "{\r\n" + 
				"   \"fields\":{\r\n" + 
				"      \"project\":{\r\n" + 
				"         \"key\":\"RES\"\r\n" + 
				"      },\r\n" + 
				"      \"summary\":\""+summary+"\",\r\n" + 
				"      \"description\":\""+description+"\",\r\n" + 
				"      \"issuetype\":{\r\n" + 
				"         \"name\":\""+issueType+"\"\r\n" + 
				"      }\r\n" + 
				"   }\r\n" + 
				"}";
	}
}
