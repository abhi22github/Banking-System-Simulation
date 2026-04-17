import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    JFrame frame = new JFrame();
    String username;
    public Home(String username){
        this.username = username;
        frame.setTitle("Home Page");
        frame.setSize(530,320);
        frame.setLayout(new BorderLayout(10,10));

        JLabel label = new JLabel("ABC Bank - For Your Service",JLabel.CENTER);
        label.setFont(new Font("Helvetica",Font.ITALIC,25));
        label.setForeground(Color.ORANGE);
        frame.add(label,BorderLayout.NORTH);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,2,20,0));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,0,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton deposit = new JButton("Deposit");
        panel.add(deposit);
        deposit.addActionListener(e -> new Deposit(username));

        JButton withdraw = new JButton("Withdraw");
        panel.add(withdraw);
        withdraw.addActionListener(e->new Withdraw(username));

        JButton transaction = new JButton("Transaction");
        panel.add(transaction);
        transaction.addActionListener(e -> new Transaction(username));

        JButton accountOverview = new JButton("Account Overview");
        panel.add(accountOverview);
        accountOverview.addActionListener(e -> new Overview(username));

        ImageIcon img = new ImageIcon("bank_Img.jpg");
        JLabel imgL = new JLabel(new ImageIcon(img.getImage()));
        mainPanel.add(panel);
        mainPanel.add(imgL);

        frame.add(mainPanel,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
