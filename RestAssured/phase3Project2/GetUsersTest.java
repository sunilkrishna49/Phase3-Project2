package phase3Project2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetUsersTest {
	final Logger logger = LogManager.getLogger(GetUsersTest.class);

	@Test
	public void getUsersTest() {
		
		RestAssured.baseURI = "https://reqres.in/api";
		
		Response res = given()
		.queryParam("page", 2)
		
		.when()
			.get("/users")
		
		.then()	
			.statusCode(200)
			.body("data.size()",equalTo(6))
			.log().all()
			.extract().response();
		
		logger.info("Response Body: "+ res.asString());
	}

}
