package checkMoney;


/* 회원아이디를 입력하고  이용시간을 입력해서  이용요금을  구하고  그 정보를   DB에 저장한다. */

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class MoneyCheck extends JFrame {

	@Autowired ClientMapper clientMapper;

	static JTextField clientId;

	static JTextField hour;
	static JLabel title;
	static JLabel moneylabel;

	static String usehour; //  이용시간 변수

	Font font = new Font("맑은 고딕", Font.BOLD, 70);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 30);


	static Thread th;

	 public MoneyCheck() {

	        setTitle("이용시간 입력창");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        Container c = getContentPane();

	        c.setLayout(new GridLayout(5, 1, 5, 5));

	        c.setBackground(Color.BLACK);



	        JLabel clientIdLabel = new JLabel("회원 아이디를 입력하세요");


	        clientIdLabel.setSize(550,80);
	        clientIdLabel.setFont(font2);
	        clientIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        clientIdLabel.setBackground(Color.BLACK);
	        clientIdLabel.setForeground(Color.white);

	        c.add(clientIdLabel);



	        clientId = new JTextField(20);
	        clientId.setFont(font);
	        clientId.setHorizontalAlignment(SwingConstants.CENTER);
	        clientId.addActionListener(new moneyaction());

	        c.add(clientId);


	        title = new JLabel(" 이용시간을 입력하고 엔터를 치세요 (숫자만 입력하세요)");


	        title.setSize(550,80);
	        title.setFont(font2);
	        title.setHorizontalAlignment(SwingConstants.CENTER);
	        title.setBackground(Color.BLACK);
	        title.setForeground(Color.white);

	        c.add(title);


	        hour = new JTextField(20);
	        hour.setFont(font);
	        hour.setHorizontalAlignment(SwingConstants.CENTER);
	        hour.addActionListener(new moneyaction());

	        c.add(hour);


	        moneylabel = new JLabel("0원");
	        moneylabel.setSize(550,80);
	        moneylabel.setFont(font);
	        moneylabel.setHorizontalAlignment(SwingConstants.CENTER);
	        moneylabel.setBackground(Color.BLACK);
	        moneylabel.setForeground(Color.white);

	        c.add(moneylabel);


	        setSize(1100,1000);
			setLocation(500,35);
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


	 public static void main(String[] args) {


		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MoneyCheck.class)
	                .headless(false).run(args);

	        EventQueue.invokeLater(() -> {

	        	MoneyCheck ex = ctx.getBean(MoneyCheck.class);
	            ex.setVisible(true);
	        });
	 }

}