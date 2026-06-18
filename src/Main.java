import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Records");

        JButton button = new JButton("Load Records");

        JTextArea area = new JTextArea(15, 40);

        button.addActionListener(e -> {

            try {
                //Connects to database
                Connection conn =
                    DriverManager.getConnection(
                        "jdbc:sqlite:student_records.db"
                    );
                //Gets all contents in enrollments
                ResultSet rs =
                    conn.createStatement()
                    .executeQuery(
                        "SELECT * FROM enrollments"
                    );

                /* 
                /Prints Names
                while(rs.next()) {

                    area.append(
                        rs.getString("first_name")
                        + " "
                        + rs.getString("last_name")
                        + "\n"
                    );
                }
                    */
                conn.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }

        });

        JTextField name = new JTextField();
      //  frame.add(button, BorderLayout.NORTH);
        frame.add(name, BorderLayout.NORTH);
        frame.add(area);

        frame.pack();
        frame.setVisible(true);
    }
}