import javax.swing.*;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import helper_classes.*;

public class ViewRecords {
  public static void main(String[] args) {
     try {

        Connection conn = DriverManager.getConnection(
            "jdbc:sqlite:student_records.db"
        );

        Statement statement = conn.createStatement();
    } catch(Exception ex) {

        ex.printStackTrace();

    }

  }

    public static void StudentClasses(Statement statement, String name) {

     JFrame frame = new JFrame(name + "'s Enrollments");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(623, 336);
     JPanel panel = new JPanel();
     panel.setLayout(null);
     panel.setBackground(Color.decode("#eeeeee"));

     JLabel title  = new JLabel("Enrollments");
     title .setBounds(59, 30, 116, 32);
     title .setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
     title .setForeground(Color.decode("#1b1b1b"));
     panel.add(title );

     JButton addclass = new JButton("Add Class");
     addclass.setBounds(460, 242, 106, 29);
     addclass.setBackground(Color.decode("#ffffff"));
     addclass.setForeground(Color.decode("#1b1b1b"));
     addclass.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
     addclass.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
     addclass.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(addclass, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
     panel.add(addclass);

     JLabel creditcount = new JLabel("Total Credits: ");
     creditcount.setBounds(62, 255, 106, 18);
     creditcount.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
     creditcount.setForeground(Color.decode("#1b1b1b"));
     panel.add(creditcount);

     frame.add(panel);
     frame.setVisible(true);

     displayRecords(statement, name);

     addclass.addActionListener(e -> {
        WindowBuilder.main(null);
     });
    }

    public static void displayRecords(Statement statement, String name) {

        JFrame frame = new JFrame(name + "'s Enrollments");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(623, 336);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#eeeeee"));


        JLabel title = new JLabel("Enrollments");
        title.setBounds(59, 30, 116, 32);
        title.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
        title.setForeground(Color.decode("#1b1b1b"));
        panel.add(title);


        JLabel element2 = new JLabel("");
        element2.setBounds(60, 70, 400, 100);
        element2.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
        element2.setForeground(Color.decode("#1b1b1b"));
        panel.add(element2);

        try {
         ResultSet rs = statement.executeQuery(
            "SELECT class_id, credits FROM enrollments WHERE first_name || last_name = '" 
            + name 
            + "'" );

            String text = "<html>";
           int totalCredits = 0;
            while(rs.next()) {

                String course = rs.getString("class_id");
                int credits = rs.getInt("credits");

                text += course + " - " + credits + " credits<br>";

                totalCredits += credits;
            }
            text += "</html>";
            element2.setText(text);
            JLabel creditcount = new JLabel(
                "Total Credits: " + totalCredits
            );

            creditcount.setBounds(62, 255, 200, 18);
            creditcount.setFont(
                CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14)
            );
            creditcount.setForeground(Color.decode("#1b1b1b"));
            panel.add(creditcount);
        } catch(SQLException e) {
            e.printStackTrace();
        }



        JButton addclass = new JButton("Add Class");
        addclass.setBounds(460, 242, 106, 29);
        addclass.setBackground(Color.decode("#ffffff"));
        addclass.setForeground(Color.decode("#1b1b1b"));
        addclass.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
        addclass.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
        addclass.setFocusPainted(false);

        OnClickEventHelper.setOnClickColor(
            addclass,
            Color.decode("#c2c2c2"),
            Color.decode("#ffffff")
        );

        panel.add(addclass);
        frame.add(panel);
        frame.setVisible(true);
    }

  
}