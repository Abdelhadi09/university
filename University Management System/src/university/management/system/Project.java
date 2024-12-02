package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    Project() {
        setTitle("UNI");
        setSize(1540, 850);
        setLocationRelativeTo(null); // Centers the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/yonsei.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setLayout(new GridBagLayout()); // Use GridBagLayout to center components on image
        add(image, BorderLayout.CENTER);

        // Semi-Transparent Background for Buttons and Welcome Label
        JPanel contentPanel = new JPanel(new BorderLayout(10, 40)) { // Add more vertical gap between components
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        contentPanel.setOpaque(false); // Make panel transparent
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around content panel

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome To ENPO", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        contentPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(6, 3, 20, 20)); // Increase gaps between buttons
        buttonPanel.setOpaque(false); // Make panel transparent

        // Adding Buttons with Border Radius
        addButtonToPanel(buttonPanel, "New Faculty Information");
        addButtonToPanel(buttonPanel, "New Student Information");
        addButtonToPanel(buttonPanel, "View Faculty Details");
        addButtonToPanel(buttonPanel, "View Student Details");
        addButtonToPanel(buttonPanel, "Faculty Leave");
        addButtonToPanel(buttonPanel, "Student Leave");
        addButtonToPanel(buttonPanel, "Faculty Leave Details");
        addButtonToPanel(buttonPanel, "Student Leave Details");
        addButtonToPanel(buttonPanel, "Examination Results");
        addButtonToPanel(buttonPanel, "Enter Marks");
        addButtonToPanel(buttonPanel, "Update Faculty Details");
        addButtonToPanel(buttonPanel, "Update Student Details");
        addButtonToPanel(buttonPanel, "Fee Structure");
        addButtonToPanel(buttonPanel, "Student Fee Form");
        addButtonToPanel(buttonPanel, "Notepad");
        addButtonToPanel(buttonPanel, "Calculator");
        addButtonToPanel(buttonPanel, "About");
        addButtonToPanel(buttonPanel, "Exit");

        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        // Adding components to the image label
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0); // Add spacing
        image.add(contentPanel, gbc);

        setVisible(true);
    }

    private void addButtonToPanel(JPanel panel, String text) {
        CustomButton button = new CustomButton(text, 15, 15);
        button.addActionListener(this);
        panel.add(button);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();

        if (msg.equals("Exit")) {
            setVisible(false);
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("New Faculty Information")) {
            new AddTeacher();
        } else if (msg.equals("New Student Information")) {
            new AddStudent();
        } else if (msg.equals("View Faculty Details")) {
            new TeacherDetails();
        } else if (msg.equals("View Student Details")) {
            new StudentDetails();
        } else if (msg.equals("Faculty Leave")) {
            new TeacherLeave();
        } else if (msg.equals("Student Leave")) {
            new StudentLeave();
        } else if (msg.equals("Faculty Leave Details")) {
            new TeacherLeaveDetails();
        } else if (msg.equals("Student Leave Details")) {
            new StudentLeaveDetails();
        } else if (msg.equals("Update Faculty Details")) {
            new UpdateTeacher();
        } else if (msg.equals("Update Student Details")) {
            new UpdateStudent();
        } else if (msg.equals("Enter Marks")) {
            new EnterMarks();
        } else if (msg.equals("Examination Results")) {
            new ExaminationDetails();
        } else if (msg.equals("Fee Structure")) {
            new FeeStructure();
        } else if (msg.equals("About")) {
            new About();
        } else if (msg.equals("Student Fee Form")) {
            new StudentFeeForm();
        }
    }

    public static void main(String[] args) {
        new Project();
    }
}
