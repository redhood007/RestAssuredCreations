package githubAPI;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
//time 2:40
import org.json.simple.JSONObject;
import org.testng.Assert;

public class GitHub {

	@BeforeTest
	public void beforeTest() {
		baseURI="https://api.github.com/user/repos";
		authentication=oauth2("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");
	}
	
  @Test(enabled=true)
  public void gettingAllRepositories() {
	  given()
	  		.auth()
	  		.oauth2("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")
	  		.when()
	  		.get()
	  		.then()
	  		.log()
	  		.body()
	  		.statusCode(200);
  }
  @Test(enabled=true)
  public void createRepositories() {
	  JSONObject data = new JSONObject();
	  data.put("name", "ApiTestingGoWooooo2");
	  data.put("description", "I am created by Rest Assured Tool B)");
	  data.put("homepage", "https://github.com/redhood007");

	  given()
	  		
	  		.header("ContentType","application/json")
	  		.body(data.toJSONString())
	  		.when()
	  		.post("https://api.github.com/user/repos")
	  		//.get("https://api.github.com/user/repos")
	  		.then()
	  		.log()
	  		.body()
	  		.statusCode(201)
	  		.time(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS);
  }
}
