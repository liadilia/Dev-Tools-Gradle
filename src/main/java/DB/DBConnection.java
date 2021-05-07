package DB;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection(){
        Connection con=null;
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
          //  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/habittracker?useSSl=false","root","P@ssword55");
            con=DriverManager.getConnection("jdbc:mysql://hosting2160545.online.pro/00490502_lgdb?useSSL=false","00490502_lgdb","00490502_lgdb");

        }catch(Exception e){System.out.println(e);}
        return con;
    }

}