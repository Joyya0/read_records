//import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;



public class RecordManager {    
        /*
            Updates all records with new enrollment
        */
        public static void addEnrollment(Statement statement, String first_name, 
                                            String last_name, int credits, String courseId) {
            
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
                                            String last_name, int credits, String course) {
            try {

                statement.executeUpdate(
                    "INSERT OR IGNORE INTO " 
                    + first_name 
                    + last_name
                    + " (course, credits) VALUES('"
                    + course + "', " 
                    + credits + ")"
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

        public static void dataToTxt(Statement statement, String name, String course, int reqCredits){
            //TODO print to txt file to send to COBOL
             PrintStream theO = null;
             int totalCredits = getTotalCredits(statement, name);
            try {
             theO = new PrintStream(new File("request.txt"));
            } catch (Exception e) {
                System.out.print("Issue creating file.");
            }
           String output = String.format("%-20s%-7s%-1d%-2d", name, course, reqCredits, totalCredits);
           theO.println(output.toUpperCase());
        }

        public static int getTotalCredits(Statement statement, String name){
            int totalCredits = 0;
            try {
            ResultSet rs = statement.executeQuery(
                "SELECT * FROM " + name
            );
            while(rs.next()) {

                int credits = rs.getInt("credits");

                totalCredits += credits;
                
            }
                 
            } catch (SQLException e) {
                 e.printStackTrace();
            }

            System.out.print("Credits: " + totalCredits);
            return totalCredits;
        }


}
