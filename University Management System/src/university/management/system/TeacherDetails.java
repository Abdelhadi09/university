package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class TeacherDetails extends JFrame implements ActionListener {

    Choice cEmpId;
    JTable table;
    RoundJButton search, print, update, add, cancel;

    TeacherDetails() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(null);
        searchPanel.setPreferredSize(new Dimension(900, 100));
        add(searchPanel, BorderLayout.NORTH);

        JLabel heading = new JLabel("Search by Employee Id");
        heading.setBounds(20, 20, 150, 30);
        searchPanel.add(heading);

        cEmpId = new Choice();
        cEmpId.setBounds(180, 20, 150, 30);
        searchPanel.add(cEmpId);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");
            while (rs.next()) {
                cEmpId.add(rs.getString("empId"));
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
            ResultSet rs = c.s.executeQuery("select * from teacher");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        add(jsp, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setPreferredSize(new Dimension(900, 100));
        add(buttonPanel, BorderLayout.SOUTH);
/*
        add = new RoundJButton("Add", 15, 15);
        add.setBounds(20, 20, 120, 30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonPanel.add(add);

        update = new RoundJButton("Update", 15, 15);
        update.setBounds(160, 20, 120, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        update.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonPanel.add(update);

        cancel = new RoundJButton("Cancel", 15, 15);
        cancel.setBounds(300, 20, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonPanel.add(cancel);
*/
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
            String query = "select * from teacher where empId = '" + cEmpId.getSelectedItem() + "'";
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
        } else if (ae.getSource() == add) {
            setVisible(false);
            new AddTeacher();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateTeacher();
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}
