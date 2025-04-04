package Assignments.assignment;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class RestCountriesApiTest8 {


	@Test(groups = {"positive"})
	public void testValidCapital() {

		RestAssured.baseURI = "https://restcountries.com/v3.1";

		// Positive test case - Valid capital name (Tallinn)

		given()
			.pathParam("capital", "tallinn")
		.when()
			.get("/capital/{capital}")
		.then()
			.assertThat()
			.statusCode(200)  // Expecting status code 200 for success
			.body("[0].capital", hasItem("Tallinn"))  // Check if the capital is Tallinn
			.body("[0].name.official", notNullValue());
	}
	
	@Test(groups = {"negative"})
	public void testInvalidCapital() {

		// Set the base URI for the API
        RestAssured.baseURI = "https://restcountries.com/v3.1";

        // Negative test case - Invalid capital name (FakeCapital)
        given()
            .pathParam("capital", "fakecapital")
        .when()
            .get("/capital/{capital}")
        .then()
            .assertThat()
            .statusCode(404)  // Expecting status code 404 for not found error
            .body("message", equalTo("Not Found")); // Verify the response message for "Not Found"
    
	}


}
