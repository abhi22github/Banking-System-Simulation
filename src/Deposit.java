import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

public class Deposit extends WDFrame implements ActionListener {
    Deposit(String username){
        super("Deposit Page","Deposit",username);
        submit.addActionListener(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            String uname = String.valueOf(this.username);
            Double amount = Double.parseDouble(amt.getText());
            String status = "Deposit of "+amount;
            try{
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking", "root", "Abhi2209!!");
                System.out.println("Database Connection Sucessful");
                PreparedStatement ps1 = conn.prepareStatement("Update balance_statement set bal = bal+? where username = ?");
                ps1.setDouble(1,amount);
                ps1.setString(2,uname);
                int r1 = ps1.executeUpdate();
                int r3=0;
                if(r1==0) {
                    PreparedStatement psr = conn.prepareStatement("Insert into balance_statement(username,bal) values(?,?)");
                    psr.setString(1, uname);
                    psr.setDouble(2, amount);
                    r3 = psr.executeUpdate();
                }
                PreparedStatement ps2 = conn.prepareStatement("Insert into account_history(username,Status,operation_date) values(?,?,?)");
                ps2.setString(1,uname);
                ps2.setString(2,status);
                ps2.setDate(3, Date.valueOf(LocalDate.now()));
                int r2 = ps2.executeUpdate();

                if((r1>0 ||r3>0) && r2>0){
                    JOptionPane.showMessageDialog(frame,"Deposit of "+amount+" Successful!!!");
                }else{
                    JOptionPane.showMessageDialog(frame,"Error in deposit");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
