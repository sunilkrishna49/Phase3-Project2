package phase3Project2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateUserTest {
	
	@Test
	public void createUserTest() {
		final Logger logger = LogManager.getLogger(CreateUserTest.class);
		RestAssured.baseURI="https://reqres.in/api";
		
		Map<String,Object> newUserData = new HashMap<>();
		newUserData.put("email", "rambo234@gmail.com");
		newUserData.put("first_name", "RamboHero");
		newUserData.put("last_name", "HeroRambo");
		
		Response res = given()
		.header("Content-Type","application/json")
		.body(newUserData)
		
			
		.when()
			.post("/users")
		.then()
			.statusCode(201)
			.body("email", equalTo("rambo234@gmail.com"))
			.body("first_name",equalTo("RamboHero"))
			.body("last_name", equalTo("HeroRambo"))
			.log().all()
			.extract().response();
		
		
		logger.info("Response Body: "+ res.asString());
	}

}
