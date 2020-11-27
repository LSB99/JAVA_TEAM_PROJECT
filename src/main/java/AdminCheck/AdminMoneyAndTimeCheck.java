package AdminCheck;

/* 관리자 페이지  회원아이디를 입력하면   해당 회원의  이용시간 , 이용요금 , 시작시간 , 종료시간 내역을 확인할 수 있다.  */

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class AdminMoneyAndTimeCheck extends JFrame {


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


	Font font = new Font("바탕", Font.BOLD, 70);

	Font font1 = new Font("맑은 고딕", Font.BOLD, 70);

	Font font2 = new Font("맑은 고딕", Font.BOLD, 45);

	Font font3 = new Font("맑은 고딕", Font.BOLD, 40);


	public AdminMoneyAndTimeCheck() {

		 setTitle("이용요금과  이용시간 체크");

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        Container c = getContentPane();

	        c.setLayout(new GridLayout(7 , 1 , 5 , 5));

	        c.setBackground(Color.BLACK);


	        JLabel clientLabel = new JLabel("회원 이용내역 조회");
	        clientLabel.setSize(550,80);
	        clientLabel.setFont(font);
	        clientLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        clientLabel.setBackground(Color.BLACK);
	        clientLabel.setForeground(Color.white);
	        c.add(clientLabel);



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
	        clientId.addActionListener(new clientaction());
	        c.add(clientId);



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


		     setLocation(100, 20);
		     setSize(1300, 1000);
		     setVisible(true);
	}


	class clientaction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {


			Client client = clientMapper.findByclientId(clientId.getText());


			moneylabel.setText("이용요금 :  "+client.getMoney());


			timelabel.setText("남은시간 :  "+client.getTime());


			starttimelabel.setText("시작시간 :  "+client.getStartDate());


			finishtimelabel.setText("종료시간 :  "+client.getEndDate());

		}

	}


	public static void main(String[] args) {

		 ConfigurableApplicationContext ctx = new SpringApplicationBuilder(AdminMoneyAndTimeCheck.class)
	                .headless(false).run(args);

	        EventQueue.invokeLater(() -> {

	        	AdminMoneyAndTimeCheck ex = ctx.getBean(AdminMoneyAndTimeCheck.class);
	            ex.setVisible(true);
	        });
	 }
}
