package Bus_reservation;

import java.sql.SQLException;

public class User {

    public boolean isRegistered(String username,String password) throws SQLException {
        UserDAO userdao=new UserDAO();
        boolean is=userdao.getRegistered(username,password);
        return is;
    }
}
