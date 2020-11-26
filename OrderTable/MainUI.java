import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bean.Food;
import dao.fooddao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ButtonGroup;

public class MainUI {

	Vector<Food> list =new Vector<Food>();
	Food food = null; 
	fooddao dao = null;
	JLabel lblNewLabel_1;
	String menu="";
	int totalPrice = 0;
		
	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainUI() {
		
		initialize();
		
	}
	
	public void initialize() {
		
		food = new Food();
		dao =new fooddao();
		
		frame = new JFrame("음식 주문");
		frame.setBounds(100, 100, 800, 750);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 170, 135));
		//panel.setBackground(new Color(255, 200, 100));
		//panel.setBackground(new Color(255, 182, 193));
		panel.setBounds(0, 0, 784, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("라면");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("라면");
				food.setPrice(3000);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		btnNewButton.setBounds(50, 37, 100, 70);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("짜장라면");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("짜장라면");
				food.setPrice(4000);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		btnNewButton_1.setBounds(200, 37, 100, 70);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("스파게티");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("스파게티");
				food.setPrice(4500);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		btnNewButton_2.setBounds(350, 37, 100, 70);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("우동");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("우동");
				food.setPrice(4600);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		btnNewButton_3.setBounds(500, 37, 100, 70);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("볶음우동");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("볶음우동");
				food.setPrice(4800);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		btnNewButton_4.setBounds(650, 37, 100, 70);
		panel.add(btnNewButton_4);
		
		JButton button = new JButton("불고기버거");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("불고기버거");
				food.setPrice(3900);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		button.setBounds(50, 148, 100, 70);
		panel.add(button);
		
		JButton button_1 = new JButton("새우버거");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("새우버거");
				food.setPrice(5100);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		button_1.setBounds(200, 148, 100, 70);
		panel.add(button_1);
		
		JButton button_2 = new JButton("치킨버거");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("치킨버거");
				food.setPrice(4300);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		button_2.setBounds(350, 148, 100, 70);
		panel.add(button_2);
		
		JButton button_3 = new JButton("햄에그버거");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("햄에그버거");
				food.setPrice(4700);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		button_3.setBounds(500, 148, 100, 70);
		panel.add(button_3);
		
		JButton button_4 = new JButton("야채버거");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("야채버거");
				food.setPrice(5000);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		button_4.setBounds(650, 148, 100, 70);
		panel.add(button_4);
		
		JButton button_5 = new JButton("콜라");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("콜라");
				food.setPrice(5000);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		button_5.setBounds(50, 266, 100, 70);
		panel.add(button_5);
		
		JButton button_6 = new JButton("사이다");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("사이다");
				food.setPrice(3000);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		button_6.setBounds(200, 266, 100, 70);
		panel.add(button_6);
		
		JButton button_7 = new JButton("오렌지주스");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("오렌지주스");
				food.setPrice(4000);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		button_7.setBounds(350, 266, 100, 70);
		panel.add(button_7);
		
		JButton button_8 = new JButton("버블티");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("버블티");
				food.setPrice(4000);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
		button_8.setBounds(500, 266, 100, 70);
		panel.add(button_8);
		
		JButton button_9 = new JButton("환타");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.setName("환타");
				food.setPrice(4000);
				menu=food.getName()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});
				
		button_9.setBounds(650, 266, 100, 70);
		panel.add(button_9);		
		//버튼
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 396, 784, 115);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("  현금");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				food.setPay("현금");
				menu=food.getName()+" / "+food.getAmount()+"개 / " +totalPrice;
				lblNewLabel_1.setText(menu);
				
				
			}
		});
		rdbtnNewRadioButton.setBounds(145, 42, 60, 26);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnCard = new JRadioButton("  카드");
		buttonGroup.add(rdbtnCard);
		rdbtnCard.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				food.setPay("카드");
				menu=food.getName()+" / "+food.getAmount()+"개 / " +totalPrice;
				lblNewLabel_1.setText(menu);
				
			}
		});
		rdbtnCard.setBounds(209, 42, 121, 26);
		panel_1.add(rdbtnCard);
		
		JButton btnMinus = new JButton("-");
		buttonGroup_1.add(btnMinus);
		btnMinus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					food.setAmount(food.getAmount()-1);
					totalPrice = food.getAmount()*food.getPrice();

				menu=food.getName()+" / "+food.getAmount()+"개 / "+food.getPay()+" / "+totalPrice;
				lblNewLabel_1.setText(menu);
			}
		});
		btnMinus.setBounds(469, 42, 80, 26);
		panel_1.add(btnMinus);
		
		JTextField textamount = new JTextField("0");
		textamount.setEditable(false);
		//buttonGroup_1.add(amount);
		/*rdbtnTall.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				food.setAmount("MEDIUM");
				food.setPrice(food.getPrice()+500);
				menu=food.getName()+" / "+food.getAmount()+" / "+food.getPay()+" / "+food.getPrice();
				lblNewLabel_1.setText(menu);
			}
		});*/
		textamount.setBounds(561, 42, 60, 26);
		panel_1.add(textamount);
		
		JButton btnPlus = new JButton("+");
		buttonGroup_1.add(btnPlus);
		btnPlus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				food.setAmount(food.getAmount()+1);
				totalPrice = food.getAmount()*food.getPrice();
				textamount.setText(""+food.getAmount());
				menu=food.getName()+" / "+food.getAmount()+"개 / "+food.getPay()+" / "+totalPrice;
				lblNewLabel_1.setText(menu);
			}
		});
		btnPlus.setBounds(633, 42, 80, 26);
		panel_1.add(btnPlus);
		
		JLabel lblNewLabel = new JLabel("결제방식");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(59, 45, 99, 18);
		panel_1.add(lblNewLabel);
		
		JLabel label = new JLabel("주문수량");
		label.setFont(new Font("Dialog", Font.BOLD, 15));
		label.setBounds(373, 45, 99, 18);
		panel_1.add(label);
		
		/*JLabel label_1 = new JLabel("사이즈");
		label_1.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1.setBounds(59, 86, 99, 18);
		panel_1.add(label_1);*/
		
		/*JRadioButton rdbtnYes = new JRadioButton("  라지");
		buttonGroup_2.add(rdbtnYes);
		rdbtnYes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				food.setSize("라지");
				food.setPrice(food.getPrice()+);
				menu=food.getName()+" / "+food.getAmount()+" / "+food.getSize()+" / "+food.getPay()+" / "+totalPrice+" 선택하였습니다.";
				lblNewLabel_1.setText(menu);
			}
		});
		rdbtnYes.setBounds(145, 83, 60, 26);
		panel_1.add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("  미디움");
		buttonGroup_2.add(rdbtnNo);
		rdbtnNo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				food.setSize("미디움");
				menu=food.getName()+" / "+food.getAmount()+" / "+food.getSize()+" / "+food.getPay()+" / "+food.getPrice()+" 선택하였습니다.";
				lblNewLabel_1.setText(menu);
			}
		});
		rdbtnNo.setBounds(209, 83, 121, 26);
		panel_1.add(rdbtnNo);*/
		//라디오버튼
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 512, 784, 217);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setText("\uCEE4\uD53C");
		
		lblNewLabel_1.setBounds(81, 40, 618, 26);
		panel_2.add(lblNewLabel_1);
		//스트링에 문자열을 넣고 액션이 나올떄마다 스트링에 값을 넣어주고 마지막으로 값을 넣어준다.
		lblNewLabel_1.setText(menu);
		
		
		//lblNewLabel_1.setText(food.getSize() +","+food.getPay()+","+ food.getAmount()+"수량" +food.getName()+"맞습니까?" + "     가격 :"+food.getPrice());
		
		JButton btnNewButton_5 = new JButton("\uACB0\uC81C");//결제 누르면 서버로 입력
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//food.setPrice(totalPrice);
				//food.setId(food.getId()+1);
				dao.foodadd(food);
				System.out.println(food);
				buttonGroup.clearSelection();
				buttonGroup_1.clearSelection();
				buttonGroup_2.clearSelection();
				lblNewLabel_1.setText("");
				JOptionPane.showMessageDialog(null, "결제 되었습니다");
				
			}
		});
		btnNewButton_5.setBounds(598, 121, 123, 38);
		panel_2.add(btnNewButton_5);
		
		JButton button_10 = new JButton("선택 취소");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				food.setName(null);
			//	food.setSize(null);
				food.setAmount(0);
				food.setPay(null);
				food.setPrice(0);
				lblNewLabel_1.setText("");
				// 버튼 초기화
				buttonGroup.clearSelection();
				buttonGroup_1.clearSelection();
				buttonGroup_2.clearSelection(); 
				System.out.println(food);
				JOptionPane.showMessageDialog(null, "선택취소되었습니다.");
				
			}
		});
		button_10.setBounds(431, 121, 123, 38);
		panel_2.add(button_10);
		
		JButton button_11 = new JButton("주문확인");//주문확인
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new SellListUI();
				System.out.println(dao.GetAllSellList());
				
				
				
			}
		});
		button_11.setBounds(57, 121, 123, 38);
		panel_2.add(button_11);
	}

	
}
