package Bus_reservation;

import java.sql.*;

public class BusDAO {
    public void displayBusInfo() throws SQLException {
        String query="select * from bus";
        Connection con=DbConnection.getConnection();

        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            System.out.print("Bus No : "+rs.getInt(1));
            if (rs.getInt(2)==0){
                System.out.print(" Non AC ");
            }
            else{
                System.out.print(" AC ");
            }
            System.out.print(" Capacity : "+rs.getInt(3));
            System.out.println();
        }
        System.out.println(".......................................................................................................");
    }
    public int getCapacity(int id) throws SQLException{
        String query="select capacity from bus where id="+id;
        Connection con=DbConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    public void addBus(Bus b) throws SQLException {

            String query ="Insert into bus (ac,capacity) values(?,?)";
            Connection con=DbConnection.getConnection();
            PreparedStatement pst=con.prepareStatement(query);
            pst.setBoolean(1, b.isAc());
            pst.setInt(2,b.getCapacity());

            int rs=pst.executeUpdate();
            if(rs==1){
                System.out.println("Added Bus Successfully !");

            }
            else{
                System.out.println("Failed to add bus !");
            }

            System.out.println("1");
    }
}
