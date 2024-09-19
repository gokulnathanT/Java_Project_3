package Bus_reservation;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Bus_Booking {
    public static void main(String[] args) throws  SQLException {

        System.out.println("welcome ! Enter your access range ");
        Scanner sc=new Scanner(System.in);
        System.out.println("Admin / Customer");
        String access=sc.nextLine();
        if(access.equalsIgnoreCase("admin")){
            BusDAO busdao = new BusDAO();
            busdao.displayBusInfo();
//            UserDAO userdao=new UserDAO();
            System.out.println("Enter the admin credentials :");
            System.out.println("USERNAME : ");
            String userName=sc.nextLine();
            System.out.println("PASSWORD : ");
            String password=sc.nextLine();
            User use=new User();
            if(use.isRegistered(userName,password)){
                while(true){
                    System.out.println("Enter the operation to perform :");
                    System.out.println("1 : Add bus");
                    System.out.println("2 : Passenger List");
                    System.out.println("3 : Exit");
                    int code=sc.nextInt();
                    switch (code){
                        case 1:
                            System.out.println("Adding bus !!!");
                            BusDAO bdao=new BusDAO();
                            Bus b=new Bus();
                            System.out.println("Enter AC / non-AC : ");
//                        int busNo = 0;
//                        b.setBusNo(busNo);
                            boolean Ac=sc.nextBoolean();
                            b.setAc(Ac);
                            System.out.println("Enter capacity : ");
                            int capa=sc.nextInt();
                            b.setCapacity(capa);
                            bdao.addBus(b);
                            break;
                        case 2:
                            System.out.println("Passenger list !");
                            BookingDAO bookdao=new BookingDAO();
                            bookdao.displayList();
                            System.out.println("..............................................................................................................");
                            break;
                        case 3:
                            System.out.println("Thank U ");
                            return;
                    }
                }

            }
            else{
                System.out.println("Enter valid credential ! ");
                return;
            }

        }
        else if(access.equalsIgnoreCase("customer")) {
            BusDAO busdao = new BusDAO();
            try {
                busdao.displayBusInfo();
                while (true) {
                    System.out.println("1 : Book Ticket \n2 : Cancel booking\n3 : Exit\nKindly select one operation :");
                    int n = sc.nextInt();
                    switch (n) {
                        case 1:
                            System.out.println("Booking started !");
                            Booking book = new Booking();
                            System.out.println("Enter name of passenger : ");
                            book.passengerName = sc.next();
                            System.out.println("Enter bus no : ");
                            book.busNo = sc.nextInt();
                            System.out.println("Enter date dd-mm-yyyy :");
                            String dateInput = sc.next();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            try {
                                book.date = dateFormat.parse(dateInput);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            if (book.isAvailable()) {
                                BookingDAO bookingdao = new BookingDAO();
                                bookingdao.addBooking(book);
                                System.out.println("Your booking is confirmed !");
                            } else {
                                System.out.println("Sorry ! Try another bus ");
                            }
                            break;
                        case 2:
                            System.out.println("Cancelling Ticket :");
                            System.out.println("Enter the booking id :");
                            int book_id = sc.nextInt();
                            Booking bo = new Booking();
                            if (bo.isPresent(book_id)) {
                                BookingDAO bdao = new BookingDAO();
                                bdao.cancelBooking(book_id);
                            } else {
                                System.out.println("No such booking exist in ID :" + book_id);
                            }
                            break;

                        case 3:
                            System.out.println("Thank U");
                            return;
                    }
                }
            }
            catch (SQLException e) {
                System.out.println(e);
                System.out.println("An error occured !");
            }
        }
        else{
            System.out.println("No such roles exists ! co");
        }

    }
}