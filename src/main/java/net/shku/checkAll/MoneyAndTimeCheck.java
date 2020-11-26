package net.shku.checkAll;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	        setTitle("이용시간 체크");
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
		     startbutton.addMouseListener(new timeaction());
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


	        	Client client = new Client();


	        	client.setClientId(clientId.getText());
	            client.setTime(hour);
	            client.setMoney(moneylabel.getText());

	            clientMapper.update(client);
	        }

	 }


	class timeaction implements MouseListener , Runnable {


		@Override
		public void mousePressed(MouseEvent e) {

			th = new Thread(new timeaction());

			th.start();
		}

		@Override
		 public void run() {


				    try {

				    	 int min = Integer.parseInt(usehour) * 60;

						 int sec = min * 60;


						 // 시작 시간
				         long startTime = System.currentTimeMillis();


				        starttimelabel.setText("시작시간 :     " + formatTime(startTime));

			      	   	for(int i=sec ; i>=0; i--){


			      		   	int h = i / 3600;

			      		   	int m = (i%3600)/60;

			      		   	int s = i%60;


			      		   	timelabel.setText("남은 시간 :     " + h +"시간 " + m + "분 " + s + "초" );

				            Thread.sleep(1000);

				        }

			      	  // 종료 시간
				         long endTime = System.currentTimeMillis();

				         // 시간 출력
				         finishtimelabel.setText("종료시간 :    " + formatTime(endTime));



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


		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

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