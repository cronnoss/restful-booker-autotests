package tests;

import api.ProjectConfig;
import api.conditions.Conditions;
import api.pojo.pojojson.BookingBody;
import api.pojo.pojojson.Bookingdates;
import api.responses.addBooking.AddBookingResponse;
import api.services.BookingService;
import api.testdatagenerate.TestDataBooking;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

@Slf4j
public class BaseTest {

    protected final static BookingService bookingService = new BookingService();
    protected final static TestDataBooking testData = new TestDataBooking();
    public static AddBookingResponse response = new AddBookingResponse();
    public static String bookingId;

    protected final BookingBody bookingBody = new BookingBody();
    protected final Bookingdates bookingdates = new Bookingdates();

    @BeforeAll
    static void setUp() {
        RestAssured.config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

        final ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        RestAssured.baseURI = config.baseURI();

        response = bookingService.addBooking(testData.testDataAddOrChangeBooking())
                .expectedResult(Conditions.statusCode(200))
                .asPojo(AddBookingResponse.class);
        bookingId = String.valueOf(response.bookingid());
    }

    @SneakyThrows
        //@AfterEach
    void cleanAfterTest() {
        bookingService.deleteBooking(bookingId)
                .expectedResult(Conditions.statusCode(201));
        log.info("bookingId was deleted: {}", bookingId);
    }
}

