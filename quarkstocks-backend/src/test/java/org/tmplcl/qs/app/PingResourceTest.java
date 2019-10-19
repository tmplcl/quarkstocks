package org.tmplcl.qs.app;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PingResourceTest {

    @Test
    public void testHelloEndpoint() {
        RestAssured.given()
          .when().get("/api/ping")
          .then()
             .statusCode(200);
    }

}
