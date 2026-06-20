//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecordManager {    
        /*
            Updates all records with new enrollment
        */
        public static void addEnrollment(Statement statement, String first_name, 
                                            String last_name, String credits, String courseId) {
            
                addNewStudent(statement, first_name, last_name, 0, courseId);
                
                try {
                    statement.executeUpdate(
                        "INSERT INTO enrollments (first_name, last_name, credits, class_id) VALUES('" 
                        + first_name + "', '" 
                        + last_name + "', " 
                        + credits + ", '" 
                        + courseId + "')"
                    );
                        updateStudentRecord(statement, first_name, last_name, credits, courseId);
                        System.out.println("Added!");

                    } catch(SQLException ex) {

                        ex.printStackTrace();
                    }
        }
        /*
          Updates student's personal record
        */
        public static void updateStudentRecord(Statement statement, String first_name, 
                                            String last_name, String credits, String course) {
            try {

                statement.executeUpdate(
                    "INSERT INTO " 
                    + first_name 
                    + last_name
                    + " (course, credits) VALUES('"
                    + course + "', " + credits + ")"
                );

                System.out.println("Added!");

            } catch(SQLException ex) {

                ex.printStackTrace();

            }
        }

        /*
          Adds new student record
        */
        public static void addNewStudent(Statement statement, String first_name, 
                                            String last_name, int creditCount, String courseId) {
            try {
                System.out.println("creating table.....");

                statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS "
                    + first_name
                    + last_name
                    + " (course TEXT PRIMARY KEY NOT NULL, "
                    + "credits INTEGER NOT NULL)"
                );
                System.out.println("table added!");

                
            } catch(SQLException ex) {

                ex.printStackTrace();

            }                         

        }


}
