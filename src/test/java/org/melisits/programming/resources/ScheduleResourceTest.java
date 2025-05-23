package org.melisits.programming.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ScheduleResourceTest {

    // Test GET /schedules/{id}
    @Test
    public void testGetScheduleById() {
        RestAssured.given()
                .pathParam("id", 1001)
                .when().get("/schedules/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", is(1001));
    }

    // Test POST /schedules
    @Test
    public void testCreateSchedule() {
        String scheduleJson = """
        {
          "id": 100,
          "from": "2025-05-23",
          "until": "2025-05-24",
          "fromTime": "09:00:00",
          "untilTime": "17:00:00",
          "allDay": false,
          "title": "Test Schedule",
          "description": "Test Description",
          "address": {"id":"ABERLE1"},
          "mark": {"id":1}
        }
        """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(scheduleJson)
                .when().post("/schedules")
                .then()
                .statusCode(201)
                .header("Location", notNullValue());
    }

    // Test PUT /schedules/{id}
    @Test
    public void testUpdateSchedule() {
        String updateJson = """
        {
          "id": 1001,
          "title": "Updated Title",
          "description": "Updated Description"
        
        }
        """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", 1001)
                .body(updateJson)
                .when().put("/schedules/{id}")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Title"));
    }

    // Test DELETE /schedules/{id}
    @Test
    public void testDeleteSchedule() {
        RestAssured.given()
                .pathParam("id", 100)
                .when().delete("/schedules/{id}")
                .then()
                .statusCode(anyOf(is(204), is(404))); // Either deleted or not found
    }
}
