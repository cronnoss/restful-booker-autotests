package tests.json;

import api.conditions.Conditions;
import api.pojo.pojojson.BookingBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UpdateTest extends BaseTest {

    @Test
    @DisplayName("Positive json update case: change data for booking")
    void updateTest() {

        bookingService.updateBooking(testData.testDataAddOrChangeBooking(), bookingId)
                .expectedResult(Conditions.statusCode(200))
                .expectedResult(Conditions.bodyField("firstname",
                        not(equalTo(response.booking().firstname()))))
                .expectedResult(Conditions.bodyField("lastname",
                        not(equalTo(response.booking().lastname()))))
                .expectedResult(Conditions.bodyField("totalprice",
                        not(equalTo(response.booking().totalprice()))))
                .expectedResult(Conditions.bodyField("checkin",
                        not(equalTo(response.booking().bookingdates().checkin()))))
                .expectedResult(Conditions.bodyField("checkout",
                        not(equalTo(response.booking().bookingdates().checkout()))))
                .expectedResult(Conditions.bodyField("additionalneeds",
                        not(equalTo(response.booking().additionalneeds()))))
                .asPojo(BookingBody.class);
    }
}

