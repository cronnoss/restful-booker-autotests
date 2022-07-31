package tests.json;

import api.conditions.Conditions;
import api.responses.addBooking.AddBookingResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import tests.BaseTest;

public class RequiredFieldsTest extends BaseTest {

    @ParameterizedTest(name = "{index} case for {arguments}")
    @DisplayName("Set of negative json tests for updating booking without one required field")
    @CsvFileSource(resources = "/testDataBooking.csv", numLinesToSkip = 1)
    void noRequiredFieldTest(String firstname, String lastname, Integer totalprice, Boolean depositpaid,
                             String checkin, String checkout, String additionalneeds, Integer status) {

        bookingBody.firstname(firstname)
                .lastname(lastname)
                .totalprice(totalprice)
                .depositpaid(depositpaid)
                .bookingdates(bookingdates
                        .checkin(checkin)
                        .checkout(checkout))
                .additionalneeds(additionalneeds);

        bookingService.updateBooking(bookingBody, bookingId)
                .expectedResult(Conditions.statusCode(status));
    }
}

