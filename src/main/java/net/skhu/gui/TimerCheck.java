package net.skhu.gui;

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

import net.skhu.dto.Client;
import net.skhu.mapper.ClientMapper;

@SpringBootApplication
@MapperScan(basePackages="net.skhu.mapper")

public class TimerCheck extends JFrame implements Runnable{


	@Autowired
    ClientMapper clientMapper;


	static JTextField name;

	static JTextField hour;
	static JLabel title;
	static JLabel moneylabel;

	static JLabel starttimelabel;

	static JLabel timelabel;

	static JLabel finishtimelabel;

	static JButton startbutton;

	static String usehour; //  이용시간 변수

	Font font = new Font("맑은 고딕", Font.BOLD, 60);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 30);


	static Thread th;

	 public TimerCheck() {

	        setTitle("이용시간 체크");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        Container c = getContentPane();

	        c.setLayout(new GridLayout(9 , 2 , 5 , 5));

	        c.setBackground(Color.BLACK);



	        JLabel namelabel = new JLabel("이름을 입력하세요");


	        namelabel.setSize(550,80);
	        namelabel.setFont(font2);
	        namelabel.setHorizontalAlignment(SwingConstants.CENTER);
	        namelabel.setBackground(Color.BLACK);
	        namelabel.setForeground(Color.white);
	        c.add(namelabel);


	        name = new JTextField(20);
	        name.setFont(font2);
	        name.setHorizontalAlignment(SwingConstants.CENTER);
	        name.addActionListener(new moneyaction());
	        c.add(name);


	        title = new JLabel(" 이용시간을 입력하고 엔터를 치세요 (숫자만 입력하세요)");


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

	        @Override
			public void actionPerformed(ActionEvent e) {

	        	String hour = e.getActionCommand();

	        	int cost = 1000*Integer.parseInt(hour);

	        	moneylabel.setText(String.valueOf(cost)+" 원");

	        	usehour = hour;


	        	Client client = new Client();


	        	client.setName(name.getText());
	            client.setTime(hour);
	            client.setMoney(moneylabel.getText());

	            clientMapper.update(client);
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


					 // 시작 시간
			         long startTime = System.currentTimeMillis();


			         starttimelabel.setText("시작시간 : " + new TimerCheck().formatTime(startTime));


		      	   	for(int i=sec ; i>=0; i--){


		      		   	int h = i / 3600;

		      		   	int m = (i%3600)/60;

		      		   	int s = i%60;


		      		   	timelabel.setText("남은 시간 : " + h +"시간 " + m + "분 " + s + "초" );

			            System.out.println("남은 시간 : " + h +"시간 " + m + "분 " + s + "초");

			            Thread.sleep(1000);

			        }

		      	  // 종료 시간
			         long endTime = System.currentTimeMillis();

			         // 시간 출력
			         finishtimelabel.setText("종료시간 : " + new TimerCheck().formatTime(endTime));

		     }

	         catch(Exception e1) {

	         	e1.printStackTrace();
	         }
	   }

	 public String formatTime(long lTime) {

	        Calendar c = Calendar.getInstance();
	        c.setTimeInMillis(lTime);

	        String time = c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 " +
                    c.get(Calendar.SECOND) + "." + c.get(Calendar.MILLISECOND) + "초";

	        return time;
	    }


	 public static void main(String[] args) {

		 th = new Thread(new TimerCheck());


		 ConfigurableApplicationContext ctx = new SpringApplicationBuilder(TimerCheck.class)
	                .headless(false).run(args);

	        EventQueue.invokeLater(() -> {

	            TimerCheck ex = ctx.getBean(TimerCheck.class);
	            ex.setVisible(true);
	        });

	 }


}