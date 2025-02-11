package Endpoints;

import org.testng.annotations.Test;

import Payload.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
// created for perform CRUD request to the user API.

public class UserEndPoints {

	
	public static Response createUser(User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
		
		return response;
	}
	
	public static Response readUser(String username)
	{
		Response response=given()
		.pathParam("username", username)
		.when()
		.post(Routes.get_url);
		
		return response;
	}
	
	
	public static Response updateUser(String username, User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", username)
		.body(payload)
		.when()
		.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String username)
	{
		Response response=given()
		.pathParam("username", username)
		.when()
		.delete(Routes.delete_url);
		
		return response;
	}
	
	
}
