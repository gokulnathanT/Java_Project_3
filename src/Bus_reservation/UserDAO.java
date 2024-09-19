package Bus_reservation;

import java.sql.*;

public class UserDAO {

    //DAO-data access object

    public boolean getRegistered(String username, String password) throws SQLException {
        String query="select * from users where username=? AND pass=?";
        Connection con=DbConnection.getConnection();
        PreparedStatement pst=con.prepareStatement(query);
        pst.setString(1,username);
        pst.setString(2,password);
        ResultSet rs=pst.executeQuery();
        if(rs.next()){
            return true;
        }
        return false;
    }
}
