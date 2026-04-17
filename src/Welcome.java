import javax.swing.*;
import java.awt.*;

public class Welcome {
     JFrame frame = new JFrame();
    JButton home = new JButton("Home");
    public Welcome(String username,String Name,int id) {
        frame.setSize(250,250);
        frame.setLayout(new BorderLayout(5,5));

        JPanel infos = new JPanel();
        infos.setLayout(new GridLayout(5,1,5,5));
        JLabel label = new JLabel(new String("Welcome "+username),JLabel.CENTER);
        label.setFont(new Font("Impact",Font.HANGING_BASELINE,20));
        infos.add(label);
        infos.add(new JLabel());
        JLabel namel = new JLabel(new String("Name :"+Name),JLabel.CENTER);
        namel.setFont(new Font("Impact",Font.ROMAN_BASELINE,20));
        infos.add(namel);
        infos.add(new JLabel());
        JLabel idl = new JLabel(new String("Id :"+id),JLabel.CENTER);
        idl.setFont(new Font("Impact",Font.HANGING_BASELINE,20));
        infos.add(idl);

        ImageIcon img = new ImageIcon("wlcome.jpg");
        JLabel imgL = new JLabel(new ImageIcon(img.getImage()));

        imgL.setLayout(new BorderLayout());
        imgL.add(infos,BorderLayout.CENTER);
        frame.add(imgL,BorderLayout.CENTER);
        frame.add(home,BorderLayout.SOUTH);
        home.addActionListener(e->new Home(username));

        infos.setOpaque(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
