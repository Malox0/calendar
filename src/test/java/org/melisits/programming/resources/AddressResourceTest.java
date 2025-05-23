package org.melisits.programming.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class AddressResourceTest {

    private static final String BASE_PATH = "/addresses";

    @BeforeEach
    public void setup() {
        // Clear DB or insert required data if needed
        // This depends on your test database config and how you want to isolate tests.
    }


    @Test
    public void testGetAllAddresses() {

        RestAssured.given()
                .when()
                .get(BASE_PATH)
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    public void testGetAddressByIdFound() {


        RestAssured.given()
                .when()
                .get(BASE_PATH + "/ABERLE1")
                .then()
                .statusCode(200)
                .body("id", equalTo("ABERLE1"))
                .body("firstName", equalTo("Elisabeth"));
    }

    @Test
    public void testGetAddressByIdNotFound() {
        RestAssured.given()
                .when()
                .get(BASE_PATH + "/nonexistent")
                .then()
                .statusCode(404);
    }

    @Test
    public void testSearchAddressesValid() {


        RestAssured.given()
                .queryParam("query", "Elisabeth")
                .when()
                .get(BASE_PATH + "/search")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    public void testSearchAddressesBlankQuery() {
        RestAssured.given()
                .queryParam("query", "")
                .when()
                .get(BASE_PATH + "/search")
                .then()
                .statusCode(400)
                .body(equalTo("Query must not be blank."));
    }




}
