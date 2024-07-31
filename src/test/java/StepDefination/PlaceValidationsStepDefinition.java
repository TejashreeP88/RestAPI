package StepDefination;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO.GoogleAPI;
import POJO.Location;
import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PlaceValidationsStepDefinition extends Utils {

	RequestSpecification res;
	ResponseSpecification Resp;
	Response response;
	static String placeId;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string}, {string} and {string}")
	public void add_place_payload_with_and(String name, String language, String address) throws IOException {
		

		// relaxedHTTPSValidation() -- bypasses SSL certification
		res = given().relaxedHTTPSValidation().spec(requestSpecification())
				.body(data.addPlacePayload(name, language, address));

	}

	@When("User calls {string} with {string} request")
	public void user_calls_with_request(String resource, String method) {
		// Enum Class // invokes the constructor
		APIResources apires = APIResources.valueOf(resource);
		apires.getResource();

		Resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("Post"))
			response = res.when().post(apires.getResource());

		else if (method.equalsIgnoreCase("Delete"))
			response = res.when().delete(apires.getResource());

		else if (method.equalsIgnoreCase("Get"))
			response = res.when().get(apires.getResource());

	}

	@Then("Response is successful with status code {int}")
	public void response_is_successful_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("Validated the response body for {string} and {string}")
	public void validated_the_response_body_for_and(String key, String value) {
		// Write code here that turns the phrase above into concrete actions

		Assert.assertEquals(getJsonPath(response, key), value);

	}

	@Then("Verify the place id created for the place {string} using {string}")
	public void verify_the_place_id_created_for_the_place_using(String name, String resource) throws IOException {

		placeId = getJsonPath(response, "place_id");
		System.out.println("Place Id is :" + placeId);
		res = given().relaxedHTTPSValidation().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_request(resource, "GET");
		String actual_name = getJsonPath(response, "name");
		Assert.assertEquals(actual_name, name);

	}

	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		res = given().relaxedHTTPSValidation().spec(requestSpecification())
				.body(data.deletePlacePayload(placeId));
	}

}
