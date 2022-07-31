package tests.xml;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static utils.JsonUtils.getNodeValue;

public class UpdateTest extends BaseTest {

    @Test
    @DisplayName("Positive xml update case: change data for booking")
    void updateTest() {

        DocumentContext jsonContext = JsonPath.parse(bookingService
                .updateBookingXml(testData.testDataAddOrChangeBookingXml(), bookingId));

        Assertions.assertNotEquals(getNodeValue(jsonContext, "$.firstname"), response.booking().firstname());
        Assertions.assertNotEquals(getNodeValue(jsonContext, "$.lastname"), response.booking().lastname());
        Assertions.assertNotEquals(getNodeValue(jsonContext, "$.totalprice"), response.booking().totalprice());
        Assertions.assertNotEquals(
                getNodeValue(jsonContext, "$.bookingdates.checkin"), response.booking().bookingdates().checkin());
        Assertions.assertNotEquals(
                getNodeValue(jsonContext, "$.bookingdates.checkout"), response.booking().bookingdates().checkout());
        Assertions.assertNotEquals(getNodeValue(
                jsonContext, "$.additionalneeds"), response.booking().additionalneeds());
    }
}

