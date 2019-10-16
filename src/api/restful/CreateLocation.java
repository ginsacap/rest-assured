package api.restful;

import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class CreateLocation {
	String location = "{"+
			  "\"location\": {"+
			    "\"lat\": -33.8669710,"+
			    "\"lng\": 151.1958750"+
			  "},"+
			  "\"accuracy\": 50,"+
			  "\"name\": \"Google Shoes!\","+
			  "\"phone_number\": \"(02) 9374 4000\","+
			  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
			  "\"types\": [\"shoe_store\"],"+
			  "\"website\": \"http://www.google.com.au/\","+
			  "\"language\": \"en-AU\""+
			"}";
	
	@Test(priority = 1)
	public void createLocation(){
		RestAssured.baseURI = "http://216.10.245.166";
		Response res = given().
		
		queryParam("key","qaclick123").
		body(location).
		when().
		post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).
		extract().response();
		System.out.println(res);
		String response = res.asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String place = js.get("place_id");
		System.out.println(place);
		given().
		queryParam("Key", "qaclick123").
		body("{"+
				"\"place_id\" : \""+place+"\""+ 
						 "}").
		when().
		post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().
		body("status", equalTo("OK"));
		
		
		
	}
	
	/*@Test(priority = 2)
	public void deleteLocation(){
		RestAssured.baseURI = "http://216.10.245.166";
		
	}*/

}
