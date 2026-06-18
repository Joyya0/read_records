
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Controller {
        public static void main(String args[]){
        
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:student_records.db");
                Statement statement = connection.createStatement();
                ResultSet names = statement.executeQuery("SELECT * FROM enrollments");

                while(names.next()) {

                    System.out.println(
                        "name: " + names.getString("first_name")
                        + " "
                        + names.getString("last_name")
                    );
                    
                }


                
              connection.close();
            } catch (SQLException e) {
                 e.printStackTrace(System.err);
            }
            


        }

}
