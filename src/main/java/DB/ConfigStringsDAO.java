package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConfigStringsDAO {

    public static String getAttributeString(String name){
        String value="";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("select value from configurations where name=?");
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                value=rs.getString("value");
            }
            con.close();
            //System.out.println(rs);
        }catch(Exception e){System.out.println(e);}
        return value;
    }
}
