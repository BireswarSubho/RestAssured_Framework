package TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Endpoints.UserEndPoints;
import Endpoints.UserEndPoints_viaProperties;
import Payload.User;
import io.restassured.response.Response;

public class UserTest {

	
	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setUpData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		
		logger.info("************Creating user************");
		
	Response response= UserEndPoints.createUser(userPayload);     //Either this or below
	response.then().log().all();
	
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("***********User is created************");
	}
	
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		logger.info("************Reading user info************");
		
		Response response=UserEndPoints_viaProperties.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************User info is displayed************");
}
	
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		
		logger.info("************Updating user info************");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************User info is updated************");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		logger.info("************Deleting user info************");
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************User info is deleted************");
}
	
}
