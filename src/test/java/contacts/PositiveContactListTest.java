package contacts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PositiveContactListTest {
	String id;

  @Test(enabled=false,description="Getting all contact list")
  public void getAllContactListInfo() {
	  given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  }
	
	@Test(enabled=false,description="Getting specific contact list")
  public void getSpecificContact() {
		given()
		  .when()
		  .get("http://3.13.86.142:3000/contacts")
		  .then()
		  .log()
		  .body()
		  .statusCode(200);
	  
  }
	
	@Test(enabled=false,description="Getting specific contact")
	public void getSpecificContact2() {
		Response res= given()
		  .when()
		  .get("http://3.13.86.142:3000/contacts/60eba23e170734047659ad54");
		
		System.out.println(res.getTime());
		
		res.then()
		.log()
		.body()
		.statusCode(200);
	}
	
	@Test(enabled=true,description="Adding specific contact list")
	  public void addingContact() {
		JSONObject details=new JSONObject();
		JSONObject loc=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject test=new JSONObject();
		
		loc.put("city","Kolkata");
		loc.put("country","India");
		emp.put("jobTitle","AT");
		emp.put("company","LTI");
		details.put("firstName","Sohaib");
		details.put("lastName","Kamal");
		details.put("email","sohaib@lti.com");
		details.put("location",loc);
		details.put("employer",emp);
		//test.put("pokemon","pikachu");
		
//		String id= given()
//					.header("Content-Type","application/json")
//					.body(details.toJSONString())
//					.when()
//				  .post("http://3.13.86.142:3000/contacts/")
//				  .then().log()
//				  .body()
//				  .statusCode(200)
//				  .extract()
//				  .path("_id");
		ExtractableResponse<Response> ex=given()
									.header("Content-Type","application/json")
									.body(details.toJSONString())
									.when()
									.post("http://3.13.86.142:3000/contacts/")
									.then().log()
									.body()
									.statusCode(200)
									.extract();
		id=ex.path("_id");
		System.out.println(ex.path("_id"));
		System.out.println(ex.path("firstName"));
		System.out.println(ex.path("lastName"));
		System.out.println(ex.path("location.city"));


	}
	
	@Test(enabled=true,dependsOnMethods="addingContact",description="Updating specific contact list")
	  public void updatingContact() {
		JSONObject details=new JSONObject();
		JSONObject loc=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject test=new JSONObject();
		
		loc.put("city","Kolkata");
		loc.put("country","India");
		emp.put("jobTitle","AT");
		emp.put("company","LTI");
		details.put("firstName","Sohaib");
		details.put("lastName","Kamal");
		details.put("email","kamal@ltiedited.com");
		details.put("location",loc);
		details.put("employer",emp);
		//test.put("pokemon","pikachu");
		
		given()
		.header("Content-Type","application/json")
		.body(details.toJSONString())
		.when()
		.put("http://3.13.86.142:3000/contacts/"+id)
		.then().log()
		.body()
		.statusCode(204);

	}
	
	@Test(enabled=true,dependsOnMethods = "updatingContact",description="Getting specific contact")
	public void getSpecificContact3() {
		given()
		  .when()
		  .get("http://3.13.86.142:3000/contacts/"+id)
		  .then()
		  .log()
		  .body()
		  .statusCode(200);
	}
	
	@Test(enabled=true,dependsOnMethods = "getSpecificContact3",description="Delete specific contact")
	public void deleteContact() {
		JSONObject details=new JSONObject();
		given()
		.header("Content-Type","application/json")
		.body(details.toJSONString())
		.when()
		.delete("http://3.13.86.142:3000/contacts/"+id)
		.then().log()
		.body()
		.statusCode(204);
	}
	
	@Test(enabled=true,dependsOnMethods = "deleteContact",description="Getting specific contact for  deleting content")
	public void getSpecificContact4() {
		given()
		  .when()
		  .get("http://3.13.86.142:3000/contacts/"+id)
		  .then()
		  .log()
		  .body()
		  .statusCode(404);
	}
}
