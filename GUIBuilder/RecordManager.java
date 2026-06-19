import java.sql.SQLException;
import java.sql.Statement;

public class RecordManager {    

        public static void addEnrollment(Statement statement, String first_name, 
                                            String last_name, String courseId2, String courseId) {
            try {
                //TODO Chesck if student already exists, add personal enrollment aswell
               statement.executeUpdate(
                "INSERT INTO enrollments (first_name, last_name, credits, class_id) VALUES('" 
                + first_name + "', '" 
                + last_name + "', " 
                + courseId2 + ", '" 
                + courseId + "')"
            );

                System.out.println("Added!");

            } catch(SQLException ex) {

                ex.printStackTrace();

            }
        }

        public static void updateStudentRecord(){

        }

        /*
          Adds new student record
        */
        public static void addNewStudent(Statement statement, String first_name, 
                                            String last_name, int creditCount, String courseId) {
            try {
                statement.executeUpdate(
                    "CREATE TABLE " + first_name + last_name + 
                    "(STRING course PRIMARY KEY NOT NULL, INTEGER credits NOT NULL);"
                );
                
            } catch(SQLException ex) {

                ex.printStackTrace();

            }                         

        }


}
