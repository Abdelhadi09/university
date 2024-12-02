package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{

    RoundJButton login, cancel;
    RoundTextField tfusername;
    RoundPasswordField tfpassword;

    Login() {
        setTitle("Login");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Title Label
        JLabel lbltitle = new JLabel("Login");
        lbltitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lbltitle.setBounds(150, 10, 100, 40);
        add(lbltitle);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(60, 70, 100, 20);
        add(lblusername);

        tfusername = new RoundTextField(15, 15, 15);
        tfusername.setBounds(170, 70, 150, 30);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(60, 120, 100, 20);
        add(lblpassword);

        tfpassword = new RoundPasswordField(15, 15, 15);
        tfpassword.setBounds(170, 120, 150, 30);
        add(tfpassword);

        login = new RoundJButton("Login", 15, 15);
        login.setBounds(60, 180, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(login);

        cancel = new RoundJButton("Cancel", 15, 15);
        cancel.setBounds(200, 180, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setSize(400, 300);
        setLocation(500, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());  // Use getPassword() for JPasswordField
            
            String query = "select * from login where username='"+username+"' and password='"+password+"'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new Project();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    setVisible(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
