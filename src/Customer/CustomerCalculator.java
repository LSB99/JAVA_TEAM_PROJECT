package Customer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CustomerCalculator extends JFrame{
	
	static JTextField hour;
	static JLabel title;
	static JLabel showlabel;
	
	Font font = new Font("맑은 고딕", Font.BOLD, 60);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 30);
	
	 public CustomerCalculator() {
		 
	        setTitle("이용시간 체크");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        GridLayout grid = new GridLayout(5, 2);
	        grid.setVgap(5);
	        Container c = getContentPane();
	        c.setLayout(grid);
	        
	        c.setBackground(Color.BLACK);  
	        
	        title = new JLabel(" 이용시간을 입력하고 엔터를 치세요 (숫자만 입력하세요)");
	        c.add(title);
	        
	        title.setLocation(10,10);  // 입력하는 수식과 계산 결과 값이 보이는 창 설정
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
			showlabel.setLocation(10,10);  // 입력하는 수식과 계산 결과 값이 보이는 창 설정
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
	        	
	        	showlabel.setText(String.valueOf(cost)+" 원");
	        	
	        }

	 }	
	 
	 
	 public static void main(String[] args) {
		 
		 new CustomerCalculator();
	 }
	        
}
