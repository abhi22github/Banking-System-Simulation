import javax.swing.*;
import java.awt.*;

public class WDFrame {
    protected JFrame frame;
    protected JTextField user,amt;
    protected JButton submit;
    protected String username;
    WDFrame(String title,String buttonName,String username){
        this.username = username;
        frame = new JFrame();
        frame.setSize(400,250);
        frame.setLayout(new BorderLayout(10,10));
        submit = new JButton(buttonName);
        JLabel label = new JLabel("ABC Bank - For Your Service",JLabel.CENTER);
        label.setFont(new Font("Helvetica",Font.ITALIC,25));
        label.setForeground(Color.ORANGE);
        frame.add(label,BorderLayout.NORTH);

        JPanel main  = new JPanel();
        main.setLayout(new GridLayout(2,2,10,10));
        main.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        JLabel amount = new JLabel("Enter Amount:");
        amount.setFont(new Font("Arial",Font.BOLD,20));
        main.add(amount);

        amt = new JTextField();
        main.add(amt);

        frame.add(main,BorderLayout.CENTER);

        submit.setBackground(Color.GREEN);
        frame.add(submit,BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
