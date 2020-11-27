package checkAll;


/* 회원아이디를 입력하고  이용시간을 입력해서  이용요금을  구하고  그 정보를   DB에 저장한다.

       그리고  시작버튼을  누르면  타이머가  시작된다.  */

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import ClientDTO.Client;
import ClientMapper.ClientMapper;

@SpringBootApplication
@MapperScan(basePackages="ClientMapper")

public class MoneyAndTimeCheck extends JFrame {


	@Autowired ClientMapper clientMapper;


	static JTextField clientId;

	static JTextField hour;

	static JLabel title;

	static JLabel moneylabel;

	static JLabel starttimelabel;

	static JLabel timelabel;

	static JLabel finishtimelabel;

	static JButton startbutton;

	static String usehour; //  이용시간 변수


	Font font1 = new Font("맑은 고딕", Font.BOLD, 70);

	Font font2 = new Font("맑은 고딕", Font.BOLD, 45);

	Font font3 = new Font("맑은 고딕", Font.BOLD, 40);


	static Thread th;

	 public MoneyAndTimeCheck() {

	        setTitle("이용요금과  이용시간 체크");

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        Container c = getContentPane();

	        c.setLayout(new GridLayout(9 , 2 , 5 , 5));

	        c.setBackground(Color.BLACK);



	        JLabel clientIdLabel = new JLabel("아이디를 입력하세요");


	        clientIdLabel.setSize(550,80);
	        clientIdLabel.setFont(font3);
	        clientIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        clientIdLabel.setBackground(Color.BLACK);
	        clientIdLabel.setForeground(Color.white);

	        c.add(clientIdLabel);



	        clientId = new JTextField(20);
	        clientId.setFont(font1);
	        clientId.setHorizontalAlignment(SwingConstants.CENTER);
	        clientId.addActionListener(new moneyaction());

	        c.add(clientId);



	        title = new JLabel(" 이용시간을 입력하고 엔터를 치세요 (숫자만 입력하세요)");


	        title.setSize(550,80);
	        title.setFont(font3);
	        title.setHorizontalAlignment(SwingConstants.CENTER);
	        title.setBackground(Color.BLACK);
	        title.setForeground(Color.white);
	        c.add(title);


	        hour = new JTextField(20);
	        hour.setFont(font1);
	        hour.setHorizontalAlignment(SwingConstants.CENTER);
	        hour.addActionListener(new moneyaction());
	        c.add(hour);


	        moneylabel = new JLabel("0원");
	        moneylabel.setSize(550,80);
	        moneylabel.setFont(new Font("바탕", Font.BOLD, 45));
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
		     startbutton.addMouseListener(new Timeaction());
		     c.add(startbutton);


		    setLocation(100, 20);
	        setSize(1700, 1000);
	        setVisible(true);

	 }


	class moneyaction implements ActionListener {

	        @Override
			public void actionPerformed(ActionEvent e) {

	        	String hour = e.getActionCommand();

	        	int cost = 1000*Integer.parseInt(hour);

	        	moneylabel.setText(String.valueOf(cost)+"원");

	        	usehour = hour;


	        	Client client = clientMapper.findByclientId(clientId.getText());  //  입력한 회원아이디로  조회

	            client.setTime(hour+"시간");

	            client.setMoney(moneylabel.getText());

	            client.setStartDate("");
	            client.setEndDate("");

	            clientMapper.update(client);
	        }

	 }


	class Timeaction extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			th = new Thread(new TimerStart());

			th.start();

		}
	}


	class TimerStart implements Runnable{


		@Override
		public void run() {


			Client client = clientMapper.findByclientId(clientId.getText());  //  입력한 회원아이디로  조회


			  try {

			    	 int min = Integer.parseInt(usehour) * 60;

					 int sec = min * 60;



			         long startTime = System.currentTimeMillis();  // 시작 시간


			        starttimelabel.setText("시작시간 :     " + formatTime(startTime));


			        client.setStartDate( formatDate(startTime) + formatTime(startTime) );

			        clientMapper.update(client);


		      	   	for(int i=sec ; i>=0; i--){


		      		   	int h = i / 3600;

		      		   	int m = (i%3600)/60;

		      		   	int s = i%60;


		      		   	timelabel.setText("남은 시간 :     " + h +"시간  " + m + "분  " + s + "초" );


				        client.setTime(h +"시간 " + m + "분 " + s + "초");

				        client.setMoney(moneylabel.getText());

				        clientMapper.update(client);

			            Thread.sleep(1000);

			        }


			         long endTime = System.currentTimeMillis();  // 종료 시간


			         finishtimelabel.setText("종료시간 :    " + formatTime(endTime));   // 시간 출력



			         // 시간종료가 되었으므로  DB에  이용요금과   이용가능한 시간을 초기화한다.

			         client.setTime("0시간");

			         client.setMoney("0원");

			         client.setEndDate( formatDate(endTime) + formatTime(endTime));

			         clientMapper.update(client);

		     }


			 catch(Exception e1) {

				 e1.printStackTrace();
			 }
		}


		public String formatTime(long lTime) {     //  현재 시분초  출력

	        Calendar c = Calendar.getInstance();
	        c.setTimeInMillis(lTime);

	        String time = c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 " + c.get(Calendar.SECOND) + "초";

	        return time;
	    }


		public String formatDate(long lTime) {    //  현재  년 , 월  , 일   출력


			Calendar c = Calendar.getInstance();

			int month = c.get(Calendar.MONTH)+1;

	        String date = c.get(Calendar.YEAR)+"년  " + month +"월  " + c.get(Calendar.DAY_OF_MONTH) +"일   ";

	        return date;

		}

	}


	 public static void main(String[] args) {

		 ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MoneyAndTimeCheck.class)
	                .headless(false).run(args);

	        EventQueue.invokeLater(() -> {

	            MoneyAndTimeCheck ex = ctx.getBean(MoneyAndTimeCheck.class);
	            ex.setVisible(true);
	        });
	 }
}


