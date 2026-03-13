package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import framework.ConfigManager;
import framework.ExtentManager;

@Epic("Public API Automation")
@Feature("API Scenarios")
public class SampleApiTest {
        @org.testng.annotations.AfterSuite
        public void tearDownSuite() {
            framework.ExtentManager.getExtentReports().flush();
        }
    @Test
    @Story("Create Post")
    @Description("Create a new post using JSONPlaceholder API and validate response.")
    @Severity(SeverityLevel.CRITICAL)
    public void createPostTest() {
        String baseUrl = ConfigManager.get("baseUrl");
        String requestBody = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .post(baseUrl + "/posts");
        response.then().log().all();
        ExtentManager.createTest("createPostTest").info("POST /posts");
        try {
            Assert.assertEquals(response.getStatusCode(), 201);
            ExtentManager.getTest().pass("Status code is 201");
            Assert.assertTrue(response.getTime() < 2000, "Response time exceeded 2 seconds: " + response.getTime() + " ms");
            ExtentManager.getTest().pass("Response time < 2s");
            Assert.assertTrue(response.getBody().asString().contains("foo"));
            Assert.assertTrue(response.getBody().asString().contains("bar"));
            ExtentManager.getTest().pass("Body contains 'foo' and 'bar'");
        } catch (AssertionError e) {
            ExtentManager.getTest().fail(e);
            throw e;
        }
    }

    @Test(groups = {"get", "jsonplaceholder"})
    @Story("Get Post")
    @Description("Get a post by ID from JSONPlaceholder API and validate response.")
    @Severity(SeverityLevel.NORMAL)
    public void getPostTest() {
        String baseUrl = ConfigManager.get("baseUrl");
        Response response = RestAssured.get(baseUrl + "/posts/1");
        response.then().log().all();
        ExtentManager.createTest("getPostTest").info("GET /posts/1");
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            ExtentManager.getTest().pass("Status code is 200");
            Assert.assertTrue(response.getBody().asString().contains("userId"));
            ExtentManager.getTest().pass("Body contains 'userId'");
        } catch (AssertionError e) {
            ExtentManager.getTest().fail(e);
            throw e;
        }
    }

    @Test(groups = {"get", "dogapi"})
    @Story("Get Random Dog Image")
    @Description("Get a random dog image from Dog CEO API and validate response.")
    @Severity(SeverityLevel.MINOR)
    public void getRandomDogImageTest() {
        Response response = RestAssured.get("https://dog.ceo/api/breeds/image/random");
        response.then().log().all();
        ExtentManager.createTest("getRandomDogImageTest").info("GET /api/breeds/image/random");
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            ExtentManager.getTest().pass("Status code is 200");
            Assert.assertTrue(response.getBody().asString().contains("message"));
            ExtentManager.getTest().pass("Body contains 'message'");
        } catch (AssertionError e) {
            ExtentManager.getTest().fail(e);
            throw e;
        }
    }

    @Test(groups = {"get", "agify"})
    @Story("Get Age By Name")
    @Description("Get age prediction by name from Agify API and validate response.")
    @Severity(SeverityLevel.TRIVIAL)
    public void getAgeByNameTest() {
        Response response = RestAssured.get("https://api.agify.io/?name=michael");
        response.then().log().all();
        ExtentManager.createTest("getAgeByNameTest").info("GET /?name=michael");
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            ExtentManager.getTest().pass("Status code is 200");
            Assert.assertTrue(response.getBody().asString().contains("age"));
            ExtentManager.getTest().pass("Body contains 'age'");
        } catch (AssertionError e) {
            ExtentManager.getTest().fail(e);
            throw e;
        }
    }

    @Test(groups = {"get", "genderize"})
    @Story("Get Gender By Name")
    @Description("Get gender prediction by name from Genderize API and validate response.")
    @Severity(SeverityLevel.TRIVIAL)
    public void getGenderByNameTest() {
        Response response = RestAssured.get("https://api.genderize.io/?name=luc");
        response.then().log().all();
        ExtentManager.createTest("getGenderByNameTest").info("GET /?name=luc");
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            ExtentManager.getTest().pass("Status code is 200");
            Assert.assertTrue(response.getBody().asString().contains("gender"));
            ExtentManager.getTest().pass("Body contains 'gender'");
        } catch (AssertionError e) {
            ExtentManager.getTest().fail(e);
            throw e;
        }
    }
}
