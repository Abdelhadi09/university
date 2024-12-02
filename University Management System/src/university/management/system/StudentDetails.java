package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class StudentDetails extends JFrame implements ActionListener {

    Choice crollno;
    JTable table;
    RoundJButton search, print;

    StudentDetails() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(null);
        searchPanel.setPreferredSize(new Dimension(900, 100));
        add(searchPanel, BorderLayout.NORTH);

        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20, 20, 150, 30);
        searchPanel.add(heading);

        crollno = new Choice();
        crollno.setBounds(180, 20, 150, 30);
        searchPanel.add(crollno);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Position the "Search" button next to the combo box
        search = new RoundJButton("Search", 15, 15);
        search.setBounds(340, 20, 120, 30);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        search.setFont(new Font("Tahoma", Font.BOLD, 15));
        searchPanel.add(search);

        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        add(jsp, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setPreferredSize(new Dimension(900, 100));
        add(buttonPanel, BorderLayout.SOUTH);

        // Position the "Print" button in the bottom right corner
        print = new RoundJButton("Print", 15, 15);
        print.setBounds(760, 20, 120, 30);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        print.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonPanel.add(print);

        setSize(900, 670);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from student where rollno = '" + crollno.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentDetails();
    }
}
