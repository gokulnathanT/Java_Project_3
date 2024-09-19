package Bus_reservation;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
public class Booking {
        String passengerName;
        int busNo;
        Date date;

        public Booking() {

        }

        public boolean isAvailable() throws SQLException {
                BusDAO busdao=new BusDAO();
                BookingDAO bookingdao=new BookingDAO();
                int capacity=busdao.getCapacity(busNo);

                int booked=bookingdao.getBookedCount(busNo,date);

                return booked<capacity;
        }

    public boolean isPresent(int bookId) throws SQLException {
            BookingDAO bookingdao=new BookingDAO();
            boolean is=bookingdao.getPresent(bookId);
            return is;
    }
}
