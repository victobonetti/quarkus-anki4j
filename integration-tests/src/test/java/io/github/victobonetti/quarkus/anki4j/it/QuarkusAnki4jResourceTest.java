package io.github.victobonetti.quarkus.anki4j.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class QuarkusAnki4jResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/quarkus-anki4j")
                .then()
                .statusCode(200)
                .body(is("Hello quarkus-anki4j"));
    }
}
