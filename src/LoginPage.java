import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage implements ActionListener{
    JFrame frame = new JFrame();
    JTextField userText = new JTextField("");
    JPasswordField passText = new JPasswordField("");
    JButton login = new JButton("Login");
    JButton signup = new JButton("Sign Up");
    LoginPage() {
        frame.setSize(700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome To ABC Bank",JLabel.CENTER);
        label.setFont(new Font("Serif",Font.ITALIC,32));
        label.setForeground(Color.MAGENTA);
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2,10,10));
        JLabel username = new JLabel("Enter Username",JLabel.CENTER);
        JLabel password = new JLabel("Enter Password",JLabel.CENTER);

        login.setBackground(Color.cyan);
        login.setPreferredSize(new Dimension(100,25));
        JButton signup = new JButton("Sign Up");
        signup.setBackground(Color.CYAN);
        signup.setPreferredSize(new Dimension(100,25));
        panel.add(username);
        panel.add(userText);
        panel.add(password);
        panel.add(passText);
        JPanel bPanel = new JPanel();
        bPanel.add(login);
        bPanel.add(signup);
        ImageIcon img = new ImageIcon("bank.png");
        JLabel imgL = new JLabel(new ImageIcon(img.getImage()));

        frame.add(imgL,BorderLayout.EAST);
        
        frame.add(panel,BorderLayout.CENTER);
        frame.add(bPanel,BorderLayout.SOUTH);
        frame.setVisible(true);

        login.addActionListener(this);
        signup.addActionListener(e->new signUp());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login){
            String userinput = userText.getText();
            String userpass = new String(passText.getPassword());
            try{
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking","root","Abhi2209!!");
                System.out.println("Database Connection Sucessful");
                PreparedStatement ps = conn.prepareStatement("Select account_id,name,password from Details where username = ?");
                ps.setString(1,userinput);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    if(userpass.equals(rs.getString("Password"))){
                        new Welcome(userinput,rs.getString("Name"),rs.getInt("account_id"));
                    }else{
                        JOptionPane.showMessageDialog(frame,"Invalid Password!!!!");
                    }
                }else{
                    JOptionPane.showMessageDialog(frame,"User Not Found !!!!");
                }
                conn.close();
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }

    }
}
