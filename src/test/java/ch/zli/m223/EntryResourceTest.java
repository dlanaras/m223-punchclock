package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Entry;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import java.time.LocalDateTime;
import java.time.Month;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
                .when().get("/entries")
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdateMethod() {
        var entry = new Entry();
        entry.setCheckIn(LocalDateTime.of(1922, Month.FEBRUARY, 8, 8, 8, 12));
        entry.setCheckOut(LocalDateTime.of(1933, Month.FEBRUARY, 8, 8, 8, 12));
        given()
                .contentType(ContentType.JSON)
                .body(entry)
                .when().post("/entries")
                .then()
                .statusCode(200)
                .body(is("{\"id\":1,\"checkIn\":\"1922-02-08T08:08:12\",\"checkOut\":\"1933-02-08T08:08:12\"}"));

        entry.setCheckIn(LocalDateTime.of(1925, Month.FEBRUARY, 8, 8, 8, 12));
        entry.setId(1L);
        // Fuck you java
        var expected = "{\"id\":1,\"checkIn\":\"1925-02-08T08:08:12\",\"checkOut\":\"1933-02-08T08:08:12\"}";
        expected = expected.replace("\\", "");
        given()
                .contentType(ContentType.JSON)
                .body(entry)
                .when().put("/entries")
                .then()
                .statusCode(200)
                .body(is(expected));
    }

    @Test
    public void testDeleteMethod() {
        var entry = new Entry();
        entry.setCheckIn(LocalDateTime.of(1922, Month.FEBRUARY, 8, 8, 8, 12));
        entry.setCheckOut(LocalDateTime.of(1933, Month.FEBRUARY, 8, 8, 8, 12));
        given()
                .contentType(ContentType.JSON)
                .body(entry)
                .when().post("/entries")
                .then()
                .statusCode(200)
                .body(is("{\"id\":2,\"checkIn\":\"1922-02-08T08:08:12\",\"checkOut\":\"1933-02-08T08:08:12\"}"));

        given()
                .when().delete("/entries/1")
                .then()
                .statusCode(204)
                .body(is(""));
    }
}