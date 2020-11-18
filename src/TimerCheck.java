import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;


public class TimerCheck extends JFrame implements Runnable{
	
	static JTextField hour;  
	static JLabel title;
	static JLabel moneylabel;
	
	static JLabel starttimelabel;
	
	static JLabel timelabel;
	
	static JLabel finishtimelabel;
	
	static JButton startbutton;
	
	static String usehour; //  �̿�ð� ����
	
	static int cost; // �̿���
	
	
	Font font = new Font("���� ���", Font.BOLD, 60);
	Font font2 = new Font("���� ���", Font.BOLD, 30);
	
	
	static Thread th;
	
	 public TimerCheck() {
		 
	        setTitle("�̿�ð� üũ");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        Container c = getContentPane();
	        
	        c.setLayout(new GridLayout(7 , 2 , 5 , 5));
	        
	        c.setBackground(Color.BLACK);  
	        
	        title = new JLabel(" �̿�ð��� �Է��ϼ��� (���ڸ� �Է��ϼ���)");
	        
	        
	        title.setSize(550,80);
	        title.setFont(font2);
	        title.setHorizontalAlignment(SwingConstants.CENTER);
	        title.setBackground(Color.BLACK);
	        title.setForeground(Color.white);        
	        c.add(title);
	        
	        
	        
	        hour = new JTextField(20);	           
	        hour.setFont(font2);
	        hour.setHorizontalAlignment(SwingConstants.CENTER);	        
	        hour.addActionListener(new moneyaction());	        
	        c.add(hour);
	        
	        
	        moneylabel = new JLabel("0");
	        moneylabel.setSize(550,80);
	        moneylabel.setFont(font);
	        moneylabel.setHorizontalAlignment(SwingConstants.CENTER);
	        moneylabel.setBackground(Color.BLACK);
	        moneylabel.setForeground(Color.white);
	        c.add(moneylabel);
			
			
			
			 starttimelabel = new JLabel("���� �ð� : ");
		     starttimelabel.setSize(550,80);
		     starttimelabel.setFont(font2);
		     starttimelabel.setHorizontalAlignment(SwingConstants.CENTER);
		     starttimelabel.setBackground(Color.BLACK);
		     starttimelabel.setForeground(Color.white);
		     c.add(starttimelabel);
		     
		     
		     timelabel = new JLabel("���� �ð� : ");
		     timelabel.setSize(550,80);
		     timelabel.setFont(font2);
		     timelabel.setHorizontalAlignment(SwingConstants.CENTER);
		     timelabel.setBackground(Color.BLACK);
		     timelabel.setForeground(Color.white);
		     c.add(timelabel);
		     
		     
		     
		     finishtimelabel = new JLabel("���� �ð� : ");
		     finishtimelabel.setSize(550,80);
		     finishtimelabel.setFont(font2);
		     finishtimelabel.setHorizontalAlignment(SwingConstants.CENTER);
		     finishtimelabel.setBackground(Color.BLACK);
		     finishtimelabel.setForeground(Color.white);
		     c.add(finishtimelabel);
		     
		     
		     
		     startbutton = new JButton("����");
		     startbutton.setSize(200,200);
		     startbutton.setFont(font2);
		     startbutton.setHorizontalAlignment(SwingConstants.CENTER);
		     startbutton.setBackground(Color.RED);
		     startbutton.setForeground(Color.white);
		     startbutton.addMouseListener(new timeaction());	    
		     c.add(startbutton);
			
	   
		    setLocation(100, 20);
	        setSize(1700, 1000);
	        setVisible(true);
	        
	 }
	 
	 
	class moneyaction implements ActionListener {
	 		
	        public void actionPerformed(ActionEvent e) {
	        	
	        	String hour = e.getActionCommand();
	            
	        	cost = 1000*Integer.parseInt(hour);
	        	
	        	moneylabel.setText(String.valueOf(cost)+" ��");
	        	
	        	usehour = hour;
	        	    	
	        }

	 }
	
	
	class timeaction extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e) {

			th.start();
		}
	
	}
		
	
	 @Override
	 public void run() {
		 
		 
			    try {
		         	
			    	 int min = Integer.parseInt(usehour) * 60;
					   
					 int sec = min * 60;
			      	
					   
			     	
			        long startTime = System.currentTimeMillis();   // ���� �ð�

			         
			        String setStartTime = "���۽ð� : " + new TimerCheck().formatTime(startTime);
			         
			         
		      	   	for(int i=sec ; i>=0; i--){
		      		   
		      	   		
		      	   		moneylabel.setText(String.valueOf(cost)+" ��");
		      	   		
		      	   		starttimelabel.setText(setStartTime);   // ���� �ð� ���
		      		     
		      		   	int h = i / 3600;
		      		   
		      		   	int m = (i%3600)/60;
		      		   
		      		   	int s = i%60;
		      		   
		      		   	
			                
		      		   	timelabel.setText("���� �ð� : " + h +"�ð� " + m + "�� " + s + "��" );
			          	
			            Thread.sleep(1000);
			            	
			        }     
		      	   	

		      	   	  
			         long endTime = System.currentTimeMillis();   // ���� �ð�
			         
			         String setEndTime = "����ð� : " + new TimerCheck().formatTime(endTime);
			         
			         finishtimelabel.setText(setEndTime);   // ���� �ð� ���
		         	
		     }
	         
	         catch(Exception e1) {
	         	
	         	e1.printStackTrace();
	         }
	   }
	 
	 public String formatTime(long lTime) {
		 
	        Calendar c = Calendar.getInstance();
	        
	        c.setTimeInMillis(lTime);
	        
	        String time = c.get(Calendar.HOUR_OF_DAY) + "�� " + c.get(Calendar.MINUTE) + "�� " +
	                      c.get(Calendar.SECOND) +  "��";
	        
	        return time;
	    }
			
	
	 public static void main(String[] args) {

		 th = new Thread(new TimerCheck());
	    
	 }     
}   