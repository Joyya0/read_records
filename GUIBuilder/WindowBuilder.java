import javax.swing.*;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import helper_classes.*;

public class WindowBuilder {

public static void main(String[] args) {

    try {

        Connection conn = DriverManager.getConnection(
            "jdbc:sqlite:student_records.db"
        );

        Statement statement = conn.createStatement();

        JFrame frame = new JFrame("Add Enrollment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(518, 278);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#eeeeee"));


        JTextField name = new JTextField("");
        name.setBounds(116, 39, 268, 21);
        name.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
        name.setBackground(Color.decode("#ffffff"));
        name.setForeground(Color.decode("#737674"));
        name.setBorder(new RoundedBorder(2, Color.decode("#626262"), 1));
        OnFocusEventHelper.setOnFocusText(
            name,
            "First Last",
            Color.decode("#1b1b1b"),
            Color.decode("#737674")
        );
        panel.add(name);



        JLabel element2 = new JLabel("Enter Student Name");
        element2.setBounds(117, 18, 133, 19);
        element2.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
        element2.setForeground(Color.decode("#1b1b1b"));
        panel.add(element2);



        JLabel element3 = new JLabel("Enter Credit Hours");
        element3.setBounds(115, 119, 121, 18);
        element3.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
        element3.setForeground(Color.decode("#1b1b1b"));
        panel.add(element3);



        JTextField course = new JTextField("");
        course.setBounds(111, 84, 269, 21);
        course.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
        course.setBackground(Color.decode("#ffffff"));
        course.setForeground(Color.decode("#737674"));
        course.setBorder(new RoundedBorder(2, Color.decode("#626262"), 1));

        OnFocusEventHelper.setOnFocusText(
            course,
            "XXXX000",
            Color.decode("#1b1b1b"),
            Color.decode("#737674")
        );

        panel.add(course);



        JLabel element5 = new JLabel("Enter Course ID");
        element5.setBounds(111, 67, 106, 17);
        element5.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
        element5.setForeground(Color.decode("#1b1b1b"));
        panel.add(element5);



        JTextField credits = new JTextField("");
        credits.setBounds(113, 135, 270, 23);
        credits.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
        credits.setBackground(Color.decode("#ffffff"));
        credits.setForeground(Color.decode("#737674"));
        credits.setBorder(new RoundedBorder(2, Color.decode("#626262"), 1));

        OnFocusEventHelper.setOnFocusText(
            credits,
            "0",
            Color.decode("#1b1b1b"),
            Color.decode("#737674")
        );

        panel.add(credits);



        JButton submit = new JButton("Submit");

        submit.setBounds(176, 177, 106, 29);
        submit.setBackground(Color.decode("#ffffff"));
        submit.setForeground(Color.decode("#1b1b1b"));
        submit.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
        submit.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
        submit.setFocusPainted(false);

        OnClickEventHelper.setOnClickColor(
            submit,
            Color.decode("#c2c2c2"),
            Color.decode("#ffffff")
        );

        panel.add(submit);
        /* 
         JButton students = new JButton("View Students");

            students.setBounds(300, 177, 130, 29);
            students.setBackground(Color.decode("#ffffff"));
            students.setForeground(Color.decode("#1b1b1b"));
            students.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
            students.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
            students.setFocusPainted(false);

            OnClickEventHelper.setOnClickColor(
                students,
                Color.decode("#c2c2c2"),
                Color.decode("#ffffff")
            );

            panel.add(students);
            */
        



        statement.setQueryTimeout(30);

        submit.addActionListener(e -> {

            String fullName = name.getText().trim();

            String[] parts = fullName.split(" ");

            if(parts.length < 2) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Enter first and last name"
                );
                return;
            }

            String first_name = parts[0];
            String last_name = parts[1];

            String courseId = course.getText();

            int creditCount;

            try {
                creditCount = Integer.parseInt(credits.getText());
            }
            catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Credits must be a number"
                );
                return;
            }


            RecordManager.addEnrollment(
                statement, first_name, last_name, creditCount, courseId
            );
            RecordManager.getTotalCredits(statement, first_name + last_name);
            RecordManager.dataToTxt(
                statement, first_name + last_name, courseId, creditCount
            );

        });
        /* 
        students.addActionListener(e ->{
            ViewRecords.StudentClasses(statement, first_name + last_name);
        });
        */


        frame.addWindowListener(
            new java.awt.event.WindowAdapter() {

                public void windowClosing(
                    java.awt.event.WindowEvent e
                ) {

                    try {

                        statement.close();
                        conn.close();

                    } catch(SQLException ex) {

                        ex.printStackTrace();

                    }

                }

            }
        );



        frame.add(panel);
        frame.setVisible(true);



    } catch(Exception ex) {

        ex.printStackTrace();

    }

}

}