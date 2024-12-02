	package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {
    
@Test
	public void testGetRequest() {
	    // Sending a GET request to fetch a specific post
	    Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");

	    // Verifying the HTTP status code returned is 200
	    Assert.assertEquals(response.getStatusCode(), 200);

	    // Ensuring the response body is not null
	    Assert.assertNotNull(response.getBody());

	    // Validating that the response contains the field "userId"
	    Assert.assertTrue(response.getBody().asString().contains("userId"));

	    /*
	     * Enhanced test to include response body validation and a meaningful assertion.
	     */
	}

@Test
public void testAllPosts() {
    // Sending a GET request to fetch all posts
    Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts");

    // Verifying the HTTP status code returned is 200
    Assert.assertEquals(response.getStatusCode(), 200);

    // Ensuring the response contains a list of posts
    Assert.assertTrue(response.getBody().jsonPath().getList("$").size() > 0);

    /*
     * Test case for validating all posts. Future improvements can include
 * validating the structure of each post in the response.
     */
}

@Test
public void testPostRequest() {
    // Sending a POST request to create a new resource
    Response response = RestAssured.given()
            .contentType("application/json")  // Setting the content type to JSON
            .body("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }")  // Payload
            .post("https://jsonplaceholder.typicode.com/posts");  // Endpoint

    // Verifying the HTTP status code returned is 201 (Created)
    Assert.assertEquals(response.getStatusCode(), 201);

    /*
     * Known Issue: This endpoint occasionally fails under heavy load.
     * Documented for future investigation and refactoring.
     */
}
}