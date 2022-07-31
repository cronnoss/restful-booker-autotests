package api.testdatagenerate;

import api.pojo.pojojson.BookingBody;
import api.pojo.pojojson.Bookingdates;
import api.pojo.pojoxml.BookingBodyXml;
import com.github.javafaker.Faker;
import utils.DateTimeUtils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static utils.StringUtils.getRandomStringOfNumbersOfLength;

public class TestDataBooking {
    private static final Faker faker = new Faker(Locale.ENGLISH);

    public BookingBody testDataAddOrChangeBooking() {
        BookingBody bookingBody = new BookingBody();
        Bookingdates bookingdates = new Bookingdates();
        long newNodeValue = Long.parseLong(getRandomStringOfNumbersOfLength(2));

        bookingBody.firstname(faker.lorem().characters(5, 25));
        bookingBody.lastname(faker.lorem().characters(5, 25));
        bookingBody.totalprice(faker.number().numberBetween(1, 10000));
        bookingBody.depositpaid(faker.bool().bool());
        bookingBody.bookingdates(
                bookingdates
                        .checkin(DateTimeUtils.getNow().plusDays(newNodeValue).format(DateTimeFormatter.ISO_DATE))
                        .checkout(DateTimeUtils.getNow().plusWeeks(newNodeValue).format(DateTimeFormatter.ISO_DATE))
        );
        bookingBody.additionalneeds(faker.lorem().characters(5, 20));
        return bookingBody;
    }

    public BookingBodyXml testDataAddOrChangeBookingXml() {
        long newNodeValue = Long.parseLong(getRandomStringOfNumbersOfLength(2));
        BookingBodyXml bookingBodyXml = new BookingBodyXml();
        BookingBodyXml.BookingdatesXml bookingdatesXml = new BookingBodyXml.BookingdatesXml();

        bookingdatesXml.setCheckin(DateTimeUtils.getNow().plusDays(newNodeValue).format(DateTimeFormatter.ISO_DATE));
        bookingdatesXml.setCheckout(DateTimeUtils.getNow().plusWeeks(newNodeValue).format(DateTimeFormatter.ISO_DATE));

        bookingBodyXml.setFirstname(faker.lorem().characters(5, 25));
        bookingBodyXml.setLastname(faker.lorem().characters(5, 25));
        bookingBodyXml.setTotalprice(faker.number().numberBetween(1, 10000));
        bookingBodyXml.setDepositpaid(faker.bool().bool());
        bookingBodyXml.setBookingdates(bookingdatesXml);
        bookingBodyXml.setAdditionalneeds(faker.lorem().characters(5, 20));
        return bookingBodyXml;
    }
}
