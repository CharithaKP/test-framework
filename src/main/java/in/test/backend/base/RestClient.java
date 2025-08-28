package in.test.backend.base;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;

import static io.restassured.RestAssured.given;

@Slf4j
public class RestClient {

    /*
     * @param requestType        HTTP Method to use
     * @param reqSpecification   request specification
     * @param responseClass      class which will be used in response object
     * @param expectedStatusCode Status code to be verified
     * @param skipOutputAsLog    default: false, put as true to not log the response or request(first boolean value is for skipping response in logs and the second boolean value is for skipping request in logs)
     * @return response in the specified class object
     */

    public <T> T getResponse(RequestType requestType,
                             RequestSpecification reqSpecification, Class<T> responseClass,Integer statusCode ){
        Response response = null;
        RequestSpecification requestSpecification = null;
        RestAssured.defaultParser = Parser.JSON;
        given().contentType("application/json\r\n");
        try{
            requestSpecification = given().spec(reqSpecification).log().all().when();
            
            System.out.println("=== MAKING " + requestType + " REQUEST ===");
            
            switch (requestType){
                case GET :
                    response = requestSpecification.get().then().extract().response();
                    break;
                case POST:
                    response = requestSpecification.post().then().extract().response();
                    break;
                case PUT:
                    response = requestSpecification.put().then().extract().response();
                    break;
                case PATCH:
                    response = requestSpecification.patch().then().extract().response();
                    break;
                case DELETE:
                    response = requestSpecification.delete().then().extract().response();
                    break;
            }
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asString());
        }
        catch (Exception e){
            System.out.println("Exception occurred: " + e.getMessage());
            System.out.println("Response Status: " + (response != null ? response.getStatusCode() : "No response"));
            System.out.println("Response Body: " + (response != null ? response.getBody().asString() : "No body"));
            Assert.fail("API call failure: " + e.getMessage());
        }
        if (response.statusCode() != statusCode) {
            Assert.fail("status code doesn't match");
        }
        if (response.getBody().asString().equals("")) {
            Reporter.log("response body is empty, returning null response", true);
            return null;
        }
        return response.as(responseClass);

    }

}
