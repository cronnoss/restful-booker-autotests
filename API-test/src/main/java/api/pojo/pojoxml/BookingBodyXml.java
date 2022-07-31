package api.pojo.pojoxml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "booking")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookingBodyXml {

    @XmlElement
    public String firstname;

    @XmlElement
    public String lastname;

    @XmlElement
    public int totalprice;

    @XmlElement
    public boolean depositpaid;

    @XmlElement(name = "bookingdates")
    public BookingdatesXml bookingdates;

    public BookingdatesXml getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingdatesXml bookingdates) {
        this.bookingdates = bookingdates;
    }

    @XmlElement
    public String additionalneeds;


    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BookingdatesXml {

        @XmlElement
        public String checkin;

        @XmlElement
        public String checkout;

        public String getCheckin() {
            return checkin;
        }

        public BookingdatesXml setCheckin(String checkin) {
            this.checkin = checkin;
            return this;
        }

        public String getCheckout() {
            return checkout;
        }

        public BookingdatesXml setCheckout(String checkout) {
            this.checkout = checkout;
            return this;
        }
    }


    public String getFirstname() {
        return firstname;
    }

    public BookingBodyXml setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public BookingBodyXml setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public BookingBodyXml setTotalprice(int totalprice) {
        this.totalprice = totalprice;
        return this;
    }

    public boolean getDepositpaid() {
        return depositpaid;
    }

    public BookingBodyXml setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
        return this;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public BookingBodyXml setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
        return this;
    }
}
