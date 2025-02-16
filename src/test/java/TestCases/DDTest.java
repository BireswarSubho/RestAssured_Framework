package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Endpoints.UserEndPoints;
import Payload.User;
import Utils.DataProviders;
import io.restassured.response.Response;

public class DDTest {

	
	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String username, String fname, String lname, String useremail, String pwd, String ph)
	{
		User userPayload= new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(username);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		
		Response response= UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2, dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String username)
	{
		Response response=UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
}
