package admin;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class RemarkController extends BaseTest {
	@Test
	public void Test01Postremark() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		String jsonBody = "{\n" + "  \"bidId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"auctionId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"userId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"remark\": \"dipanshu\"\n" + // No
																													// trailing
																													// comma
																													// here
				"}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// Send the POST request
		Response response = request.post("/remark");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Handle different status codes
		switch (statusCode) {
		case 200:
			System.out.println("Request succeeded: Data inserted successfully.");
			break;
		case 201:
			System.out.println("Request succeeded: Symmetry created.");
			break;
		case 400:
			System.out.println("Bad Request: Invalid input.");
			break;
		case 403:
			System.out.println("Forbidden: Access is denied.");
			break;
		case 500:
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
			break;
		default:
			System.out.println("Unexpected status code received: " + statusCode);
		}

		// Assert that the status code is 200 (OK) or 201 (Created)
		Assert.assertTrue(statusCode == 200 || statusCode == 201,
				"Expected 200 OK or 201 Created, but got: " + statusCode);
	}

	public void Test02RemarkCount() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		// Add query parameters for GET request (username, password, lotDiamondInfo)

		// request.queryParam("lotJewelleryInfo", ""); // Add lotDiamondInfo parameter

		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
		// String lotJewelleryInfo ="";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);
		// request.pathParam("lotJewelleryInfo", lotJewelleryInfo);

		// Send the GET request with query parameters
		Response response = request.get("/remark/count/{userId}/{auctionId}");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 403 (Forbidden)
		if (statusCode == 403) {
			System.out.println("Access denied: Invalid credentials or permissions.");
		} else if (statusCode == 200) {
			System.out.println("Request succeeded: Access granted.");
		}

		// Assert that the status code is 200 (OK), or change the expectation if 403 is
		// valid
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void Test03RemarkCount() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Specify the remarkId as a path parameter
		// String remarkId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
		// request.pathParam("remarkId", remarkId);

		// Send the DELETE request
		Response response = request.delete("/remark/delete");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 204 (No Content)
		switch (statusCode) {
		case 200:
			System.out.println("Request succeeded: Remark deleted successfully.");
			break;
		case 204:
			System.out.println("Request succeeded: No Content, remark deleted.");
			break;
		case 403:
			System.out.println("Forbidden: Access is denied.");
			break;
		case 404:
			System.out.println("Not Found: The specified resource was not found.");
			break;
		case 500:
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
			break;
		default:
			System.out.println("Unexpected status code: " + statusCode);
		}

		// Assert that the status code is 200 (OK) or 204 (No Content)
		Assert.assertTrue(statusCode == 200 || statusCode == 204,
				"Expected 200 OK or 204 No Content, but got: " + statusCode);

		// Parse and log specific fields from the response body
		if (statusCode == 200) {
			String responseBody = response.getBody().asString();
			System.out.println("Parsed Response Body: " + responseBody);

			// Example of extracting JSON fields
			String bidId = response.jsonPath().getString("bidId");
			String lotId = response.jsonPath().getString("lotId");
			String auctionId = response.jsonPath().getString("auctionId");
			String userId = response.jsonPath().getString("userId");
			String remark = response.jsonPath().getString("remark");

			System.out.println("Bid ID: " + bidId);
			System.out.println("Lot ID: " + lotId);
			System.out.println("Auction ID: " + auctionId);
			System.out.println("User ID: " + userId);
			System.out.println("Remark: " + remark);
		}
	}
}
