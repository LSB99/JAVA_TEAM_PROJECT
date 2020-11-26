package net.skhu.checkTime;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import net.skhu.dto.Client;
import net.skhu.mapper.ClientMapper;

@SpringBootApplication
@MapperScan(basePackages="net.skhu.mapper")

public class TimerCheck extends JFrame {


	@Autowired ClientMapper clientMapper;

	static JTextField clientId;

	static JLabel starttimelabel;

	static JLabel timelabel;

	static JLabel finishtimelabel;

	static JButton startbutton;


	static Client client;

	static String usehour; //  이용시간 변수

	Font font1 = new Font("맑은 고딕", Font.BOLD, 75);

	Font font2 = new Font("맑은 고딕", Font.BOLD, 50);


	static Thread th;

	 public TimerCheck() {

	        setTitle("이용시간 체크");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        Container c = getContentPane();

	        c.setLayout(new GridLayout(5 , 1 , 5 , 5));

	        c.setBackground(Color.BLACK);



	        JLabel clientIdLabel = new JLabel("아이디를 입력하세요");


	        clientIdLabel.setSize(550,80);
	        clientIdLabel.setFont(font2);
	        clientIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        clientIdLabel.setBackground(Color.BLACK);
	        clientIdLabel.setForeground(Color.white);
	        c.add(clientIdLabel);


	        clientId = new JTextField(20);
	        clientId.setFont(font1);
	        clientId.setHorizontalAlignment(SwingConstants.CENTER);
	        clientId.addActionListener(new TimeAction());
	        c.add(clientId);



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



		    setLocation(100, 20);
	        setSize(1700, 1000);
	        setVisible(true);

	 }


	public class TimeAction implements ActionListener , Runnable{


		@Override
		public void actionPerformed(ActionEvent e) {


			String hour = e.getActionCommand();


			client = clientMapper.findByclientId(hour);


			usehour = client.getTime();


			th = new Thread(new TimeAction());

			th.start();

		}


		 @Override
		 public void run() {

				    try {

				    	 int min = Integer.parseInt(usehour) * 60;

						 int sec = min * 60;


						 // 시작 시간
				         long startTime = System.currentTimeMillis();


				         starttimelabel.setText("시작시간 :   " + formatTime(startTime));


			      	   	for(int i=sec ; i>=0; i--){


			      		   	int h = i / 3600;

			      		   	int m = (i%3600)/60;

			      		   	int s = i%60;


			      		   	timelabel.setText("남은 시간 :   " + h +"시간 " + m + "분 " + s + "초" );

				            Thread.sleep(1000);

				        }

			      	  // 종료 시간
				         long endTime = System.currentTimeMillis();

				         // 시간 출력
				         finishtimelabel.setText("종료시간 : " + formatTime(endTime));



				         // 시간종료가 되었으므로  DB에  이용요금과   이용가능한 시간을 초기화한다.

				         Client client = new Client();

						 client.setClientId(clientId.getText());
				         client.setTime("0");
				         client.setMoney("0원");

				         clientMapper.update(client);


			     }

		         catch(Exception e1) {

		         	e1.printStackTrace();
		         }
		   }



		 public String formatTime(long lTime) {

		        Calendar c = Calendar.getInstance();
		        c.setTimeInMillis(lTime);

		        String time = c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 " +
	                    c.get(Calendar.SECOND) + "초";

		        return time;
		 }
	}


	 public static void main(String[] args) {

		 ConfigurableApplicationContext ctx = new SpringApplicationBuilder(TimerCheck.class)
	                .headless(false).run(args);

	        EventQueue.invokeLater(() -> {

	            TimerCheck ex = ctx.getBean(TimerCheck.class);
	            ex.setVisible(true);
	        });

	 }
}