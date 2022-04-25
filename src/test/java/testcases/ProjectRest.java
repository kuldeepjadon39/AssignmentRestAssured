package testcases;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ProjectRest {
	
	
	
	public String URL = "https://petstore.swagger.io/";
	public Response methodGet() {
		Response res = given().when().get(URL);
		return res;
	}
	public Response methodGet(String value) {
		Response res = given().when().get(URL + value);
		return res;
	}
	
	public Response methodPostToCreateUser() throws IOException {
		FileInputStream file = new FileInputStream(
				new File(System.getProperty("user.dir") + "\\data\\user.json"));
		String createValue = "v2/user/createWithArray";
		Response res = given().body(IOUtils.toByteArray(file)).header("Content-type", "application/json").when()
				.post(URL + "v2/user/createWithArray");
		return res;
	}
	

	@Test(description = "to demostarte the get method")
	public void getGenericMethod() throws IOException {
		
		String value="v2/user/Kuldeep";
		Response res = methodPostToCreateUser();
		System.out.println("PATH = " + res.asString());
		assertEquals(res.getStatusCode(), 200);
		Response res1 = methodGet(value);
		System.out.println("Response = " + res1.asString());
		assertEquals(res1.getStatusCode(), 200);

	}

	@Test(description = "post Method")
	public void postMethod() {

		String value="v2/user/createWithList";
		Response res = given()
				.body("[\r\n" + "  {\r\n" + "    \"id\": 0,\r\n" + "    \"username\": \"Kuldeep\",\r\n"
						+ "    \"firstName\": \"string\",\r\n" + "    \"lastName\": \"string\",\r\n"
						+ "    \"email\": \"string\",\r\n" + "    \"password\": \"string\",\r\n"
						+ "    \"phone\": \"string\",\r\n" + "    \"userStatus\": 0\r\n" + "  }\r\n" + "]")

				.header("Content-Type", "application/json").when()
				.post(URL+value);

		System.out.println(res.asString());

	}

	@Test(description = "post Method with json file")
	public void postMethodWithjsonFile() throws IOException {

		String value="v2/user/createWithList";
		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "//data/user.json"));

		Response res = given().body(IOUtils.toString(file))

				.header("Content-Type", "application/json").when()
				.post(URL+value);

		System.out.println(res.asString());

	}

	@Test(description = "put Method")
	public void putMethod() {

		String value="v2/user/Kuldeep";
		Response res = given()
				.body("{\r\n" + "  \"id\": 0,\r\n" + "  \"username\": \"string\",\r\n"
						+ "  \"firstName\": \"string\",\r\n" + "  \"lastName\": \"string\",\r\n"
						+ "  \"email\": \"string\",\r\n" + "  \"password\": \"string\",\r\n"
						+ "  \"phone\": \"string\",\r\n" + "  \"userStatus\": 0\r\n" + "}")

				.header("Content-Type", "application/json").when().put(URL+value);

		System.out.println(res.asString());

	}

	@Test(description = "put Method with json file")
	public void putMethodWithjsonFile() throws IOException {
		String value="v2/user/Kuldeep";
		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "//data/user2Put.json"));

		Response res = given().body(IOUtils.toString(file))

				.header("Content-Type", "application/json").when().put(URL+value);

		System.out.println(res.asString());

	}

	@Test(description = "Delete Method")
	public void deleteMethod() {

		String value="v2/user/Kuldeep";
		Response res = given().header("Content-Type", "application/json").when()
				.delete(URL+value);
		System.out.println("Response is " + res.asString());
		assertEquals(res.getStatusCode(), 200);

	}

	@Test(description = "POST,GET,PUT,DELETE Method")
	public void verificationcode404() {
		//POST
		Response res = given()
				.body("[\r\n" + "  {\r\n" + "    \"id\": 0,\r\n" + "    \"username\": \"string\",\r\n"
						+ "    \"firstName\": \"string\",\r\n" + "    \"lastName\": \"string\",\r\n"
						+ "    \"email\": \"string\",\r\n" + "    \"password\": \"string\",\r\n"
						+ "    \"phone\": \"string\",\r\n" + "    \"userStatus\": 0\r\n" + "  }\r\n" + "]")

				.header("Content-Type", "application/json").when()
				.post(URL+"v2/user/createWithList/Kuldeep");

		System.out.println(res.asString());
		assertEquals(res.getStatusCode(), 404);
		
		//GET
		res = given().when().get(URL+"v2/user/KuldeepRock");
		System.out.println("Response is " + res.asString());
		assertEquals(res.getStatusCode(), 404);
		
		//DELETE
		res = given().header("Content-Type", "application/json").when()
				.delete(URL+"v2/user/KuldeepRock");
		System.out.println("Response is " + res.asString());
		assertEquals(res.getStatusCode(), 404);

	}

}
