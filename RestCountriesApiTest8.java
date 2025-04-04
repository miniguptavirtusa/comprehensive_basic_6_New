package Assignments.assignment;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class RestCountriesApiTest8 {


	@Test(groups = {"positive"})
	public void testValidCapital() {

		RestAssured.baseURI = "https://restcountries.com/v3.1";

		// Positive test case - Valid capital name (Tallinn)

		try {
			Response response =

				given()
					.pathParam("capital", "tallinn")
				.when()
					.get("/capital/{capital}")
				.then()
					.assertThat()
					.statusCode(200)  // Expecting status code 200 for success
					.body("[0].capital", hasItem("Tallinn"))  // Check if the capital is Tallinn
					.body("[0].name.official", notNullValue())
					.extract().response();

			System.out.println("Response: " + response.getBody().asString());

		} catch (Exception e) {
			System.err.println("An error occurred while making the API request.");
			e.printStackTrace();
		}
	}

	@Test(groups = {"negative"})
	public void testInvalidCapital() {

		// Set the base URI for the API
		RestAssured.baseURI = "https://restcountries.com/v3.1";

		// Negative test case - Invalid capital name (FakeCapital)
		try {
			Response response =
				given()
					.pathParam("capital", "fakecapital")
				.when()
					.get("/capital/{capital}")
				.then()
					.assertThat()
					.statusCode(404)  // Expecting status code 404 for not found error
					.body("message", equalTo("Not Found")) // Verify the response message for "Not Found"
					.extract().response();

			System.out.println("Response: " + response.getBody().asString());

		} catch (Exception e) {
			System.err.println("An error occurred while making the API request.");
			e.printStackTrace();
		}
	}


}
