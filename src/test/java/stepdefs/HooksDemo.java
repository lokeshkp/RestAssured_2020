package stepdefs;

import java.io.IOException;

import io.cucumber.java.Before;

public class HooksDemo {


	@Before("@sanity")
	public void beforeScenario() throws IOException {
		AddPlaceTest apt = new AddPlaceTest();

		if(AddPlaceTest.place_id==null) {
			apt.add_place_payload_with("Lokesh", "Telugu", "Oduru");
			apt.user_calls_with_post_http_request("AddPlaceAPI", "POST");
			apt.verify_place_id_created_maps_to_using("Lokesh", "GetPlaceAPI");
		}
	}
}
