package StepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{

		PlaceValidationsStepDefinition stepDefinition = new PlaceValidationsStepDefinition();
		
		if(stepDefinition.placeId==(null))
		{
		stepDefinition.add_place_payload_with_and("Tej", "Marathi", "Asia");
		stepDefinition.user_calls_with_request("AddPlaceAPI", "POST");
		stepDefinition.verify_the_place_id_created_for_the_place_using("Tej", "GetPlaceAPI");
		}		
	}

}
