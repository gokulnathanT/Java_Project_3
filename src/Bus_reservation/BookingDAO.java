package Bus_reservation;

import java.sql.*;
import java.util.Date;

public class BookingDAO {

    public int getBookedCount(int busNo, Date date) throws SQLException {
        String query="select count(passenger_name) from booking where bus_no=? and travel_date=?;";
        Connection con=DbConnection.getConnection();
        PreparedStatement pst=con.prepareStatement(query);
        java.sql.Date sqldate=new java.sql.Date(date.getTime());
        pst.setInt(1,busNo);
        pst.setDate(2,sqldate);
        ResultSet rs=pst.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public void addBooking(Booking book) throws SQLException{
        String query ="Insert into booking (passenger_name,bus_no,travel_date) values(?,?,?)";
        Connection con=DbConnection.getConnection();
        java.sql.Date sqldate=new java.sql.Date(book.date.getTime());

        PreparedStatement pst=con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, book.passengerName);
        pst.setInt(2,book.busNo);
        pst.setDate(3,sqldate);
        pst.executeUpdate();
        ResultSet rs=pst.getGeneratedKeys();
        if(rs.next()){
            int generatedId=rs.getInt(1);
            System.out.println("Booking added with ID: "+generatedId);
        }

        System.out.println("1");

    }

    public boolean getPresent(int bookId) throws SQLException {
        String query="select count(passenger_name) from booking where Booking_id="+bookId;
        Connection con=DbConnection.getConnection();
        Statement pst=con.createStatement();
        ResultSet rs=pst.executeQuery(query);
        if(rs.next()){
            return true;
        }
        return false;
    }

    public void cancelBooking(int id) throws SQLException {
        String query ="delete from booking where Booking_id=?";
        Connection con=DbConnection.getConnection();
        PreparedStatement pst=con.prepareStatement(query);
        pst.setInt(1,id);
        int rs=pst.executeUpdate();
        if(rs>0){
            System.out.println("Booking cancelled for ID : "+id );
        }
        else{
            System.out.println("Cancellation failed : No such booking exist !");
        }


    }

    public void displayList() throws SQLException {
        String query="select * from booking ";
        Connection con=DbConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            System.out.print(" Booking ID : "+rs.getInt(4)+" | ");
            System.out.print(" Passenger Name : "+rs.getString(1)+" | ");
            System.out.print(" Bus No : "+rs.getInt(2)+" | ");
            System.out.print(" Travel Date : "+rs.getDate(3)+" | ");
            System.out.println();
        }
    }
}
