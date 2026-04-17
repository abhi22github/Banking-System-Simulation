import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Overview{
    JFrame frame = new JFrame();
    String username;
    Overview(String username){
        this.username = username;
        frame.setSize(580,280);
        frame.setLayout(new BorderLayout(10,10));
        JLabel label = new JLabel("Account Overview",JLabel.CENTER);
        label.setFont(new Font("Helvetica",Font.ITALIC,25));
        label.setForeground(Color.BLUE);
        frame.add(label,BorderLayout.NORTH);

        String[] cols = {"Id","Description","Date"};
        ArrayList<TransactionRecord> lst = new ArrayList<>();
        DefaultTableModel model = new DefaultTableModel(cols,0);
        frame.add(new JScrollPane(new JTable(model)),BorderLayout.CENTER);

        display(lst,model,label);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void display(ArrayList<TransactionRecord> lst,DefaultTableModel model,JLabel label){
        String uname= this.username;
        try{
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking", "root", "Abhi2209!!");
                System.out.println("Database Connection Sucessful");
                PreparedStatement ps = conn.prepareStatement("Select bal from balance_statement where username =?");
                ps.setString(1,uname);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Double balance = rs.getDouble("bal");
                    label.setText("Current Balance : "+balance+" rupees");
                }

                PreparedStatement ps2 = conn.prepareStatement("Select Tid,Status,operation_date from account_history where username = ?");
                ps2.setString(1,uname);
                ResultSet rs2 = ps2.executeQuery();

            while(rs2.next()){
                lst.add(new TransactionRecord(
                        rs2.getInt("Tid"),
                        rs2.getString("Status"),
                        rs2.getDate("operation_date")
                ));
            }
            for(TransactionRecord tr : lst){
                model.addRow(new Object[]{tr.id, tr.status, tr.date});
            }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


class TransactionRecord{
    Integer id;
    String status;
    Date date;

    public TransactionRecord(Integer id,String status, Date date) {
        this.id = id;
        this.status = status;
        this.date = date;
    }
}