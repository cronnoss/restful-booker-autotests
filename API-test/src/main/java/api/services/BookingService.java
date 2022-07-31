package api.services;

import api.ProjectConfig;
import api.asserts.AssertableResponse;
import api.pojo.pojojson.BookingBody;
import api.pojo.pojoxml.BookingBodyXml;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

@Slf4j
public class BookingService extends ApiService {

    final static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    static String token = config.token();

    protected static final RequestSpecification requestSpecificationXml = new RequestSpecBuilder()
            .setBaseUri(config.baseURI())
            .addHeader("Authorization", "Basic " + token)
            .setContentType("text/xml")
            .log(LogDetail.ALL)
            .build();

    protected static final ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();


    public AssertableResponse addBooking(BookingBody bookingBody) {
        return new AssertableResponse(
                setupRequestSpec()
                        .body(bookingBody)
                        .when()
                        .post()
        );
    }

    public AssertableResponse getBookingById(String bookingId) {
        return new AssertableResponse(
                setupRequestSpec()
                        .when()
                        .get("/" + bookingId)
        );
    }

    public AssertableResponse updateBooking(BookingBody bookingBody, String bookingId) {
        return new AssertableResponse(
                setupRequestSpec()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Basic " + token)
                        .body(bookingBody)
                        .when()
                        .put("/" + bookingId)
        );
    }

    public String updateBookingXml(BookingBodyXml bookingBodyXml, String bookingId) {
        return given()
                .spec(requestSpecificationXml)
                .body(bookingBodyXml)
                .when()
                .put("/" + bookingId)
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .extract()
                .body()
                .asString();
    }

    public AssertableResponse deleteBooking(String bookingId) {
        return new AssertableResponse(
                setupRequestSpec()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Basic " + token)
                        .when()
                        .delete("/" + bookingId)
        );
    }
}
