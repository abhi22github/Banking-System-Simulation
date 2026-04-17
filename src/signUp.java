import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signUp implements ActionListener {
    JFrame frame = new JFrame();
    JTextField name;
    JSpinner age;
    JTextArea address;
    JTextField Phone;
    JTextField DOB;
    JTextField aadhar;
    JRadioButton male, female;
    JComboBox<String> Occupation, AccountType;
    JTextField username;
    JPasswordField password;
    JButton button = new JButton("Create");

    signUp() {
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setTitle("Account Creation Form");
        JLabel heading = new JLabel("Create Your Account", JLabel.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setForeground(Color.MAGENTA);
        frame.add(heading, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Name:"));
        name = new JTextField();
        panel.add(name);

        panel.add(new JLabel("Age:"));
        age = new JSpinner(new SpinnerNumberModel(18, 1, 120, 1));
        panel.add(age);

        panel.add(new JLabel("Address:"));
        address = new JTextArea();
        panel.add(address);

        panel.add(new JLabel("D.O.B(DD-MM-YYYY:"));
        DOB = new JTextField();
        panel.add(DOB);

        panel.add(new JLabel("Aadhar:"));
        aadhar = new JTextField();
        panel.add(aadhar);

        panel.add(new JLabel("Phone:"));
        Phone = new JTextField();
        panel.add(Phone);

        panel.add(new JLabel("Gender:"));
        male = new JRadioButton("M");
        female = new JRadioButton("F");
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        JPanel gPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gPanel.add(male);
        gPanel.add(female);
        panel.add(gPanel);

        panel.add(new JLabel("Occupation:"));
        String[] jobs = {"Private", "Government", "Student", "Others"};
        Occupation = new JComboBox<>(jobs);
        panel.add(Occupation);

        panel.add(new JLabel("Account Type:"));
        String[] types = {"Current", "Savings"};
        AccountType = new JComboBox<>(types);
        panel.add(AccountType);

        panel.add(new JLabel("Username:"));
        username = new JTextField();
        panel.add(username);

        panel.add(new JLabel("Password:"));
        password = new JPasswordField();
        panel.add(password);

        JScrollPane scroll = new JScrollPane(panel);
        frame.add(scroll, BorderLayout.CENTER);

        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(Color.cyan);
        frame.add(button, BorderLayout.SOUTH);
        button.addActionListener(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String uname = name.getText();
            int uage = (int) age.getValue();
            String uaddress = address.getText();
            String uPhone = Phone.getText();
            Long ulphone = Long.parseLong(uPhone);
            String udob = DOB.getText();
            String uaadhar = aadhar.getText();
            String ugender = male.isSelected() ? "M" : "F";
            String uOcc = (String) Occupation.getSelectedItem();
            String uAccType = AccountType.getSelectedItem().equals("Savings") ? "Savings" : "Current";
            String usernm = username.getText();
            String upass = new String(password.getPassword());

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking", "root", "Abhi2209!!");
                System.out.println("Database Connection Sucessful");
                PreparedStatement ps = conn.prepareStatement("Insert into details(name,age,address,DOB,Phone,aadhar,gender,Occupation,username,password,Account_Type) values(?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, uname);
                ps.setInt(2, uage);
                ps.setString(3, uaddress);
                ps.setString(4, udob);
                ps.setLong(5, ulphone);
                ps.setString(6, uaadhar);
                ps.setString(7, ugender);
                ps.setString(8, uOcc);
                ps.setString(9, usernm);
                ps.setString(10, upass);
                ps.setString(11, uAccType);

                int result = ps.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(frame, "Account Created Sucessfully");
                    frame.dispose();
                    new LoginPage();
                } else {
                    System.out.println("DBMS Error");
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Database Error");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                System.out.println("SQL Boss Error");
            }
        }
    }
}



