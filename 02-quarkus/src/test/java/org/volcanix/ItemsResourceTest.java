package org.volcanix;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ItemsResourceTest {

    @Test
    public void testEndpoint() {
        given()
                .when().get("/items")
                .then()
                .statusCode(200)
                .body(containsString("Appsssle:"), containsString("<del>30</del> <strong>27.0</strong>"));
    }

}
