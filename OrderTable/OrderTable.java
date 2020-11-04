/* 현재 상황: 주문화면, 메뉴 및 수량 선택, 주문 시 내역확인 및 기존기록 초기화
 * 보완필요 요소: 마지막으로 입력한 수량이 초기화 전까지 다른 주문에도 영향을 끼치는 문제, 주문한 음식들의 총 비용, 결제방식 추가 
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OrderTable {
	int count = 0;
	int totalPrice = 0;
	String list = "";

	public OrderTable() {
		JFrame frame = new JFrame("PC방 음식 주문");
		
		//GridLayout layout = new GridLayout(0, 4, 40, 40);
		frame.setBounds(0, 0, 625, 1000);
	    frame.setBackground(Color.black);
	 
	    // 폰트
	    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	    Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
	 
	    // 화면 상단
	    Panel pNorth = new Panel();
	    //pNorth.setBackground(new Color(255, 255, 215));
	    //pNorth.setBackground(new Color(102, 204, 204));
	    pNorth.setBackground(new Color(102, 153, 204));
	   // pNorth.setLayout(layout);
	    pNorth.setLayout(null);
	    pNorth.setSize(0, 500);
	    pNorth.setFont(font);
	 
	    
	    // 배열 설정 부분
	    String textHeaders[] = {"상품명", "단가", "수량", "합계"};
	    String menuTable[][];
	    
	    String menu[] = { "신라면", "불고기버거 세트", "닭강정", "메뉴3", "메뉴4", "메뉴5", "메뉴6", "메뉴7" };
	    int price[] = { 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500 };
	   
	    JButton bt[] = new JButton[menu.length];
	    TextField suja[] = new TextField[menu.length];
	    Button minus[] = new Button[menu.length];
	    Button plus[] = new Button[menu.length];
	    JButton ok[] = new JButton[menu.length];
	    Label l[] = new Label[menu.length];
	    ImageIcon icon[] = new ImageIcon[menu.length];
	 
	    // 버튼 설정 부분
	    for (int i = 0; i < menu.length; i++) {
	 
	        // 선택 버튼
            bt[i] = new JButton(menu[i]);

            if (i < 4) {
            	bt[i].setBounds(25 + i * 150, 50, 100, 100);
	            } else {
	                bt[i].setBounds(25 + (i - 4) * 150, 300, 100, 100);
	            }
	            icon[i] = new ImageIcon(i + ".png");
	            bt[i].setIcon(icon[i]);
	 
	            // 숫자 텍스트 버튼
	            suja[i] = new TextField("0");
	            suja[i].setBackground(Color.white);
	            suja[i].setEditable(false);
	            suja[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 130, 40, 20);
	 
	            // "-" 버튼
	            minus[i] = new Button("-");
	            minus[i].setBounds(bt[i].getX(), suja[i].getY(), 20, 20);
	            minus[i].setEnabled(false);
	 
	            // "+" 버튼
	            plus[i] = new Button("+");
	            plus[i].setBounds(bt[i].getX() + (100 - 20), suja[i].getY(), 20, 20);
	            plus[i].setEnabled(false);
	 
	            // 가격
	            l[i] = new Label(price[i] + "원");
	            l[i].setBounds(bt[i].getX() + 20, suja[i].getY() - 25, 100, 20);
	 
	            // 확인 버튼
	            ok[i] = new JButton("확인");
	            ok[i].setBounds(bt[i].getX(), suja[i].getY() + 30, 100, 20);
	            ok[i].setEnabled(false);
	 
	            pNorth.add(bt[i]);
	            pNorth.add(suja[i]);
	            pNorth.add(minus[i]);
	            pNorth.add(plus[i]);
	            pNorth.add(l[i]);
	            pNorth.add(ok[i]);
	        }
	 
	        // 화면 중앙 주문정리
	       /* TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
	        ta.setText("   상품명        단가        수량        합계\n\n");
	        ta.setBackground(Color.white);
	        ta.setEditable(false);
	        ta.setFont(font1);*/
	        
	        JTable table;
	        JScrollPane scroll;
	        String[] [] data;
	        String[] title = {"상품명","단가","수량","합계"};
	        table = new JTable(3,4);
	        
	        data = new String[3][4];
	        data[0][0]="";
	        data[0][1]="";
	        data[0][2]="";
	        data[0][3]="";
	        
	        data[1][0]="";
	        data[1][1]="";
	        data[1][2]="";
	        data[1][3]="";
	        
	        data[2][0]="";
	        data[2][1]="";
	        data[2][2]="";
	        data[2][3]="";
	        
	        table = new JTable(data,title); 
	        scroll = new JScrollPane(table);
	        
	        // 화면 하단
	        Panel pSouth = new Panel();
	        pSouth.setFont(font);
	        //pSouth.setBackground(new Color(255, 255, 215));
	        pSouth.setBackground(new Color(102, 204, 204));
	        
	        Button bt1 = new Button("주문");
	        Button bt2 = new Button("초기화");
	        Button bt3 = new Button("닫기");
	        pSouth.add(bt1);
	        pSouth.add(bt2);
	        pSouth.add(bt3);
	 
	        // 주문버튼
	        bt1.addActionListener(new ActionListener() {
	 
	            @Override
	            public void actionPerformed(ActionEvent e) {
	           //     JOptionPane.showMessageDialog(null, ta.getText() + " 주문되었습니다. \n이용해주셔서 감사합니다.");
	                for (int i = 0; i < menu.length; i++) {
	                    bt[i].setEnabled(true);
	                    minus[i].setEnabled(false);
	                    plus[i].setEnabled(false);
	                    suja[i].setText("0");
	                //    ta.setText("   상품명        단가        수량        합계\n\n");
	 
	                }
	            }
	        });
	 
	        // 초기화 버튼
	        bt2.addActionListener(new ActionListener() {
	 
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                for (int i = 0; i < menu.length; i++) {
	                    bt[i].setEnabled(true);
	                    minus[i].setEnabled(false);
	                    plus[i].setEnabled(false);
	                    suja[i].setText("0");
	           //         ta.setText("   상품명        단가        수량        합계\n\n");
	 
	                }
	            }
	        });
	 
	 
	        //bt3 닫기버튼
	        
	        bt3.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
	        });
	 
	 
	        // 컴포넌트
	        frame.add(pNorth, BorderLayout.NORTH);
	        frame.add(table, BorderLayout.CENTER);
	        //frame.add(ta, BorderLayout.CENTER);
	        frame.add(pSouth, BorderLayout.SOUTH);
	        frame.setVisible(true);
	 
	        // 이벤트단
	        for (int i = 0; i < menu.length; i++) {
	            int j = i;
	 
	            // 주문 버튼 이벤트
	            bt[i].addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    minus[j].setEnabled(true);
	                    plus[j].setEnabled(true);
	                    bt[j].setEnabled(false);
	                    ok[j].setEnabled(true);
	 
	                    count = 0;
	                }
	            });
	 
	            // "-" 버튼 이벤트
	            minus[i].addActionListener(new ActionListener() {
	 
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    if (count > 0) {
	                        count = count - 1;
	                        suja[j].setText(count + "");
	                        ok[j].setEnabled(true);
	                    } else {
	                        minus[j].setEnabled(false);
	                    }
	                }
	            });
	            
	            // "+" 버튼 이벤트
	            plus[i].addActionListener(new ActionListener() {
	 
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    count = count + 1;
	                    suja[j].setText(count + "");
	                    ok[j].setEnabled(true);
	                    if (count > 0) {
	                        minus[j].setEnabled(true);
	                    }
	                }
	            });
	            
	            //확인 버튼 이벤트
	            ok[i].addActionListener(new ActionListener() {
	 
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    list = bt[j].getActionCommand();
	               //     ta.append("   " + list + "       " + price[j] + "        " + count + "         " + price[j] * count
	                //            + "원" + "\n");
	                    ok[j].setEnabled(false);
	                }
	            });
	 
	        }
	 
	        // 끄기
	        frame.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                System.exit(0);
	            }
	        });
	    }
	public static void main(String[] args) {
		
		new OrderTable();
	}
	}
