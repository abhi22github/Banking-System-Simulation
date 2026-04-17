import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

public class Transaction implements ActionListener {
    JFrame frame = new JFrame();
    JButton submit = new JButton("Transaction");
    JTextField user,amt,user2;
    String username;
    Transaction(String username){
        this.username = username;
        frame.setTitle("Transaction Page");
        frame.setSize(500,250);
        frame.setLayout(new BorderLayout(10,10));

        JLabel label = new JLabel("ABC Bank - For Your Service",JLabel.CENTER);
        label.setFont(new Font("Helvetica",Font.ITALIC,25));
        label.setForeground(Color.ORANGE);
        frame.add(label,BorderLayout.NORTH);

        JPanel main  = new JPanel();
        main.setLayout(new GridLayout(3,2,10,10));
        main.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

//        JLabel username = new JLabel("Enter Username of Sender:");
//        username.setFont(new Font("Arial",Font.BOLD,15));
//        main.add(username);

//        user = new JTextField();
//        main.add(user);

        JLabel username2 = new JLabel("Enter Username of Receiver:");
        username2.setFont(new Font("Arial",Font.BOLD,14));
        main.add(username2);

        user2 = new JTextField();
        main.add(user2);

        JLabel amount = new JLabel("Enter Amount:");
        amount.setFont(new Font("Arial",Font.BOLD,15));
        main.add(amount);

        amt = new JTextField();
        main.add(amt);

        submit.addActionListener(this);
        frame.add(main,BorderLayout.CENTER);

        submit.setBackground(Color.GREEN);
        frame.add(submit,BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            String uname1 = this.username;
            String uname2 = new String(user2.getText());
            Double amount = Double.parseDouble(amt.getText());
            String status1 = "Transaction  of "+amount+" to "+uname2;
            String status2 = "Received  of "+amount+" from "+uname1;

            try{
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking", "root", "Abhi2209!!");
                System.out.println("Database Connection Sucessful");
                double balance = 0.0;
                PreparedStatement balCheck = conn.prepareStatement("Select bal from balance_statement where username=?");
                balCheck.setString(1,uname1);
                ResultSet rs = balCheck.executeQuery();
                if(rs.next()){
                    balance = rs.getDouble("bal");
                    if((balance-amount)<5000.00){
                        JOptionPane.showMessageDialog(frame,"Insufficient amount for transaction!!!!");
                        frame.dispose();
                    }
                }
                PreparedStatement ps1 = conn.prepareStatement("Update balance_statement set bal = bal-? where username = ?");
                ps1.setDouble(1, amount);
                ps1.setString(2, uname1);
                int r1 = ps1.executeUpdate();


                PreparedStatement ps2 = conn.prepareStatement("Update balance_statement set bal = bal+? where username = ?");
                ps2.setDouble(1, amount);
                ps2.setString(2, uname2);
                int r2 = ps2.executeUpdate();

                PreparedStatement ps3 = conn.prepareStatement("Insert into account_history(username,Status,operation_date) values(?,?,?)");
                ps3.setString(1, uname1);
                ps3.setString(2, status1);
                ps3.setDate(3, Date.valueOf(LocalDate.now()));
                int r3 = ps3.executeUpdate();

                PreparedStatement ps4 = conn.prepareStatement("Insert into account_history(username,Status,operation_date) values(?,?,?)");
                ps4.setString(1, uname2);
                ps4.setString(2, status2);
                ps4.setDate(3, Date.valueOf(LocalDate.now()));
                int r4 = ps4.executeUpdate();

                if (r1>0 && r2>0 && r3>0 && r4>0) {
                    JOptionPane.showMessageDialog(frame, "Transaction of " + amount + " Successful!!!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Error in Transaction");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
