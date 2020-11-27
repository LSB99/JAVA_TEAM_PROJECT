package checkTime;

/* 회원아이디를 입력해서  DB에 저장되어있는  기존에  사용하고 남은시간 불러와  이어서 사용하는 기능이다. */

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

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

import ClientDTO.Client;
import ClientMapper.ClientMapper;

@SpringBootApplication
@MapperScan(basePackages="ClientMapper")

public class TimerCheck extends JFrame {


	@Autowired ClientMapper clientMapper;

	static JTextField clientId;

	static JLabel starttimelabel;

	static JLabel timelabel;

	static JLabel finishtimelabel;

	static JButton startbutton;


	static Client client;

	static String usehour; //  이용시간 변수


	static int min;

	static int sec;


	static boolean beforeTime = false; // 기존에 사용하지 않은 시간이 있는지 체크

	Font font1 = new Font("맑은 고딕", Font.BOLD, 75);

	Font font2 = new Font("맑은 고딕", Font.BOLD, 50);


	static Thread th;

	 public TimerCheck() {

	        setTitle("기존에 남은 이용시간 이어서 사용하기");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        Container c = getContentPane();

	        c.setLayout(new GridLayout(5 , 1 , 5 , 5));

	        c.setBackground(Color.BLACK);



	        JLabel clientIdLabel = new JLabel("회원 아이디를 입력하세요 (엔터를 치면 타이머가 시작됩니다.)");


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


	class TimeAction implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {


			String clientInformation = e.getActionCommand();


			client = clientMapper.findByclientId(clientInformation);


			usehour = client.getTime();


			StringTokenizer times = new StringTokenizer(usehour , "시간분초 ");


			ArrayList<String> time = new ArrayList<String>();


			while(times.hasMoreTokens()) {

				time.add(times.nextToken());
			}



			if(time.size() > 1) {   // 기존에  사용하고 남은 시간이 있는 경우

				beforeTime = true;

				usehour = time.get(0);

				min = Integer.parseInt(time.get(1));

				sec = Integer.parseInt(time.get(2));

			}



			if(beforeTime==false) {    // 처음 이용하는 경우

				min = Integer.parseInt(time.get(0)) * 60;

				sec = min * 60;

			}


			if(beforeTime==true) {   // 기존에  사용하고 남은 시간이 있는 경우

				sec = sec + (min*60) + (Integer.parseInt(usehour)*60*60);
			}


			th = new Thread(new TimerStart());

			th.start();

		}
	}


	class TimerStart implements Runnable{


		@Override
		public void run() {


			Client client = clientMapper.findByclientId(clientId.getText());  //  입력한 회원아이디로  조회


			  try {

			        long startTime = System.currentTimeMillis();  // 시작 시간


			        starttimelabel.setText("시작시간 :     " + formatTime(startTime));


			        client.setStartDate( formatDate(startTime) + formatTime(startTime) );

			        clientMapper.update(client);


			        for(int i=sec ; i>=0; i--){

		      		   	int h = i / 3600;

		      		   	int m = (i%3600)/60;

		      		   	int s = i%60;


		      		   	timelabel.setText("남은 시간 :     " + h +"시간 " + m + "분 " + s + "초" );


				        client.setTime(h +"시간 " + m + "분 " + s + "초");

				        client.setMoney(client.getMoney());

				        clientMapper.update(client);

			            Thread.sleep(1000);

			        }

			         long endTime = System.currentTimeMillis();  // 종료 시간


			         finishtimelabel.setText("종료시간 :    " + formatTime(endTime));  // 시간 출력



			         // 시간종료가 되었으므로  DB에  이용요금과   이용가능한 시간을 초기화한다.

			         client.setTime("0");

			         client.setMoney("0원");


			         client.setEndDate( formatDate(endTime) + formatTime(endTime));

			         clientMapper.update(client);

		     }

			 catch(Exception e1) {

				 e1.printStackTrace();
			 }
		}


		public String formatTime(long lTime) {   //  현재 시분초  출력

	        Calendar c = Calendar.getInstance();

	        c.setTimeInMillis(lTime);

	        String time = c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 " + c.get(Calendar.SECOND) + "초";

	        return time;
	    }


		public String formatDate(long lTime) {   //  현재  년 , 월  , 일   출력


			Calendar c = Calendar.getInstance();

			int month = c.get(Calendar.MONTH)+1;

			String date = c.get(Calendar.YEAR)+"년  " + month +"월  " + c.get(Calendar.DAY_OF_MONTH) +"일   ";

	        return date;

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