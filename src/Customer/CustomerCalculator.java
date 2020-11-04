package Customer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CustomerCalculator extends JFrame{
	
	static JTextField hour;
	static JLabel title;
	static JLabel showlabel;
	
	Font font = new Font("���� ���", Font.BOLD, 60);
	Font font2 = new Font("���� ���", Font.BOLD, 30);
	
	 public CustomerCalculator() {
		 
	        setTitle("�̿�ð� üũ");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        GridLayout grid = new GridLayout(5, 2);
	        grid.setVgap(5);
	        Container c = getContentPane();
	        c.setLayout(grid);
	        
	        c.setBackground(Color.BLACK);  
	        
	        title = new JLabel(" �̿�ð��� �Է��ϰ� ���͸� ġ���� (���ڸ� �Է��ϼ���)");
	        c.add(title);
	        
	        title.setLocation(10,10);  // �Է��ϴ� ���İ� ��� ��� ���� ���̴� â ����
	        title.setSize(550,80);
	        title.setFont(font2);
	        title.setHorizontalAlignment(SwingConstants.CENTER);
	        title.setBackground(Color.BLACK);
	        title.setForeground(Color.white);
	        
	        
	        hour = new JTextField(20);
	        
	        c.add(hour);
	        
	        hour.setFont(font2);
	        hour.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        hour.addActionListener(new moneyaction());
	        
	        
	        showlabel = new JLabel("0");
	        c.add(showlabel);
			showlabel.setLocation(10,10);  // �Է��ϴ� ���İ� ��� ��� ���� ���̴� â ����
			showlabel.setSize(550,80);
			showlabel.setFont(font);
			showlabel.setHorizontalAlignment(SwingConstants.CENTER);
			showlabel.setBackground(Color.BLACK);
			showlabel.setForeground(Color.white);
	   
	        setSize(800, 800);
	        setVisible(true);
	        
	 } 
	 
	 class moneyaction implements ActionListener {
	 		
	        public void actionPerformed(ActionEvent e) {
	        	
	        	String hour = e.getActionCommand();
	            
	        	int cost = 1000*Integer.parseInt(hour);
	        	
	        	showlabel.setText(String.valueOf(cost)+" ��");
	        	
	        }

	 }	
	 
	 
	 public static void main(String[] args) {
		 
		 new CustomerCalculator();
	 }
	        
}
