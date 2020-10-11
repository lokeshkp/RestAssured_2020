Feature: Validating Plave API's

	@smoke
  Scenario Outline: Verify Place is being Sucessfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then The API call got success with status code 200
    #And "status" in response body is "OK"
    And "scope"	in response body is "APP"
		And Verify place_id created maps to "<name>" using "GetPlaceAPI"
    Examples: 
      | name    | language | address  |
      | Pavani  | English  | Oduru    |
      | Dileep  | Spanish  | Oduru-II |
      | Karthik | Telugu   | Oduru-I  |

	@sanity      
	Scenario: Verify if Delete place functionality is working
		Given Delete Place Payload
		When User calls "DeletePlaceAPI" with "DELETE" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"