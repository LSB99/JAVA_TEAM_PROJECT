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
	
	static String usehour; //  이용시간 변수
	
	static int cost; // 이용요금
	
	
	Font font = new Font("맑은 고딕", Font.BOLD, 60);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 30);
	
	
	static Thread th;
	
	 public TimerCheck() {
		 
	        setTitle("이용시간 체크");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        Container c = getContentPane();
	        
	        c.setLayout(new GridLayout(7 , 2 , 5 , 5));
	        
	        c.setBackground(Color.BLACK);  
	        
	        title = new JLabel(" 이용시간을 입력하세요 (숫자만 입력하세요)");
	        
	        
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
			
			
			
			 starttimelabel = new JLabel("시작 시간 : ");
		     starttimelabel.setSize(550,80);
		     starttimelabel.setFont(font2);
		     starttimelabel.setHorizontalAlignment(SwingConstants.CENTER);
		     starttimelabel.setBackground(Color.BLACK);
		     starttimelabel.setForeground(Color.white);
		     c.add(starttimelabel);
		     
		     
		     timelabel = new JLabel("남은 시간 : ");
		     timelabel.setSize(550,80);
		     timelabel.setFont(font2);
		     timelabel.setHorizontalAlignment(SwingConstants.CENTER);
		     timelabel.setBackground(Color.BLACK);
		     timelabel.setForeground(Color.white);
		     c.add(timelabel);
		     
		     
		     
		     finishtimelabel = new JLabel("종료 시간 : ");
		     finishtimelabel.setSize(550,80);
		     finishtimelabel.setFont(font2);
		     finishtimelabel.setHorizontalAlignment(SwingConstants.CENTER);
		     finishtimelabel.setBackground(Color.BLACK);
		     finishtimelabel.setForeground(Color.white);
		     c.add(finishtimelabel);
		     
		     
		     
		     startbutton = new JButton("시작");
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
	        	
	        	moneylabel.setText(String.valueOf(cost)+" 원");
	        	
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
			      	
					   
			     	
			        long startTime = System.currentTimeMillis();   // 시작 시간

			         
			        String setStartTime = "시작시간 : " + new TimerCheck().formatTime(startTime);
			         
			         
		      	   	for(int i=sec ; i>=0; i--){
		      		   
		      	   		
		      	   		moneylabel.setText(String.valueOf(cost)+" 원");
		      	   		
		      	   		starttimelabel.setText(setStartTime);   // 시작 시간 출력
		      		     
		      		   	int h = i / 3600;
		      		   
		      		   	int m = (i%3600)/60;
		      		   
		      		   	int s = i%60;
		      		   
		      		   	
			                
		      		   	timelabel.setText("남은 시간 : " + h +"시간 " + m + "분 " + s + "초" );
			          	
			            Thread.sleep(1000);
			            	
			        }     
		      	   	

		      	   	  
			         long endTime = System.currentTimeMillis();   // 종료 시간
			         
			         String setEndTime = "종료시간 : " + new TimerCheck().formatTime(endTime);
			         
			         finishtimelabel.setText(setEndTime);   // 종료 시간 출력
		         	
		     }
	         
	         catch(Exception e1) {
	         	
	         	e1.printStackTrace();
	         }
	   }
	 
	 public String formatTime(long lTime) {
		 
	        Calendar c = Calendar.getInstance();
	        
	        c.setTimeInMillis(lTime);
	        
	        String time = c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 " +
	                      c.get(Calendar.SECOND) +  "초";
	        
	        return time;
	    }
			
	
	 public static void main(String[] args) {

		 th = new Thread(new TimerCheck());
	    
	 }     
}   