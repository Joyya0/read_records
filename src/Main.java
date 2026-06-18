


import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Records");

        JButton button = new JButton("Load Records");

        JTextArea area = new JTextArea(15, 40);

        button.addActionListener(e -> {

            try {

                Connection conn =
                    DriverManager.getConnection(
                        "jdbc:sqlite:student_records.db"
                    );

                ResultSet rs =
                    conn.createStatement()
                    .executeQuery(
                        "SELECT * FROM enrollments"
                    );


                while(rs.next()) {

                    area.append(
                        rs.getString("first_name")
                        + " "
                        + rs.getString("last_name")
                        + "\n"
                    );
                }

            } catch(Exception ex) {
                ex.printStackTrace();
            }

        });


        frame.add(button, BorderLayout.NORTH);
        frame.add(area);

        frame.pack();
        frame.setVisible(true);
    }
}