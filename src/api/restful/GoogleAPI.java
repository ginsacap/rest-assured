package api.restful;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
/*
 * given()
 * Request Headers, Request cookies, params.
 * when()
 * get(resource),post(resource),put(resource).
 * then()
 * assertions()
 * extract()
 * pull the response and place it in a variable for the future use.
 * 
 * Parameters
 * Path parameters - Which will be used in the path.
 * Query parameters - Query parameters used mlstly in POST request.
 * Header parameters - which we send in the header.
 */
public class GoogleAPI {
	@Test
	public void getLocation(){
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		String response = given().
		param("location","-33.8670522,151.1957362").
	       param("radius","500").
	       param("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
	       when().
	       get("/maps/api/place/nearbysearch/json").
	       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	       //body("results[0].name", equals("Sydney")).and().
	      // body("results[0].place_id", equals("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	       header("Server","scaffolding on HTTPServer2").
	       extract().asString();
		System.out.println(response);
	}
	

}
