package utils;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

	@BeforeSuite
	public void beforeSuite() {
	    try {
	        System.out.println("BeforeSuite");

	        RestAssured.baseURI = "http://localhost:8080/api/v1";
	        RequestSpecification request = RestAssured.given();

	        JSONObject requestParams = new JSONObject();
	        requestParams.put("username", Constant.adminUserName);
	        requestParams.put("password", Constant.adminPassword);

	        request.header("Content-Type", "application/json");
	        request.body(requestParams.toString());

	        Response response = request.post("/auth/authenticate");
	        System.out.println("The status received: " + response.statusLine());

	        int statusCode = response.getStatusCode();
	        String responseBody = response.getBody().asString();

	        if (responseBody == null || responseBody.isEmpty()) {
	            throw new RuntimeException("Response body is null or empty.");
	        }

	        JSONObject jsonObject = new JSONObject(responseBody);
	        if (!jsonObject.has("accessToken")) {
	            throw new RuntimeException("Response JSON does not contain 'accessToken'.");
	        }

	        Constant.authToken = jsonObject.getString("accessToken");
	        System.out.println("Before suite Token: " + Constant.authToken);
	        Assert.assertEquals(statusCode, 200);

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error in @BeforeSuite: " + e.getMessage(), e);
	    }
	}


	@AfterSuite
	public void afterSuite() {

		System.out.println("AfterSuite");
	}

}