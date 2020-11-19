/* ���� ��Ȳ: �ֹ�ȭ��, �޴� �� ���� ����, �ֹ� �� ����Ȯ�� �� ������� �ʱ�ȭ
 * �����ʿ� ���: ���������� �Է��� ������ �ʱ�ȭ ������ �ٸ� �ֹ����� ������ ��ġ�� ����, �ֹ��� ���ĵ��� �� ���, ������� �߰� 
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OrderTable {
	int count = 0;
	int totalPrice = 0;
	String list = "";

	public OrderTable() {
		JFrame frame = new JFrame("PC�� ���� �ֹ�");
		
		//GridLayout layout = new GridLayout(0, 4, 40, 40);
		frame.setBounds(0, 0, 625, 1000);
	    frame.setBackground(Color.black);
	 
	    // ��Ʈ
	    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	    Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
	 
	    // ȭ�� ���
	    Panel pNorth = new Panel();
	    //pNorth.setBackground(new Color(255, 255, 215));
	    //pNorth.setBackground(new Color(102, 204, 204));
	    pNorth.setBackground(new Color(102, 153, 204));
	   // pNorth.setLayout(layout);
	    pNorth.setLayout(null);
	    pNorth.setSize(0, 500);
	    pNorth.setFont(font);
	 
	    
	    // �迭 ���� �κ�
	    String textHeaders[] = {"��ǰ��", "�ܰ�", "����", "�հ�"};
	    String menuTable[][];
	    
	    String menu[] = { "�Ŷ��", "�Ұ����� ��Ʈ", "�߰���", "�޴�3", "�޴�4", "�޴�5", "�޴�6", "�޴�7" };
	    int price[] = { 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500 };
	   
	    JButton bt[] = new JButton[menu.length];
	    TextField suja[] = new TextField[menu.length];
	    Button minus[] = new Button[menu.length];
	    Button plus[] = new Button[menu.length];
	    JButton ok[] = new JButton[menu.length];
	    Label l[] = new Label[menu.length];
	    ImageIcon icon[] = new ImageIcon[menu.length];
	 
	    // ��ư ���� �κ�
	    for (int i = 0; i < menu.length; i++) {
	 
	        // ���� ��ư
            bt[i] = new JButton(menu[i]);

            if (i < 4) {
            	bt[i].setBounds(25 + i * 150, 50, 100, 100);
	            } else {
	                bt[i].setBounds(25 + (i - 4) * 150, 300, 100, 100);
	            }
	            icon[i] = new ImageIcon(i + ".png");
	            bt[i].setIcon(icon[i]);
	 
	            // ���� �ؽ�Ʈ ��ư
	            suja[i] = new TextField("0");
	            suja[i].setBackground(Color.white);
	            suja[i].setEditable(false);
	            suja[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 130, 40, 20);
	 
	            // "-" ��ư
	            minus[i] = new Button("-");
	            minus[i].setBounds(bt[i].getX(), suja[i].getY(), 20, 20);
	            minus[i].setEnabled(false);
	 
	            // "+" ��ư
	            plus[i] = new Button("+");
	            plus[i].setBounds(bt[i].getX() + (100 - 20), suja[i].getY(), 20, 20);
	            plus[i].setEnabled(false);
	 
	            // ����
	            l[i] = new Label(price[i] + "��");
	            l[i].setBounds(bt[i].getX() + 20, suja[i].getY() - 25, 100, 20);
	 
	            // Ȯ�� ��ư
	            ok[i] = new JButton("Ȯ��");
	            ok[i].setBounds(bt[i].getX(), suja[i].getY() + 30, 100, 20);
	            ok[i].setEnabled(false);
	 
	            pNorth.add(bt[i]);
	            pNorth.add(suja[i]);
	            pNorth.add(minus[i]);
	            pNorth.add(plus[i]);
	            pNorth.add(l[i]);
	            pNorth.add(ok[i]);
	        }
	 
	        // ȭ�� �߾� �ֹ�����
	       /* TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
	        ta.setText("   ��ǰ��        �ܰ�        ����        �հ�\n\n");
	        ta.setBackground(Color.white);
	        ta.setEditable(false);
	        ta.setFont(font1);*/
	        
	        JTable table;
	        JScrollPane scroll;
	        String[] [] data;
	        String[] title = {"��ǰ��","�ܰ�","����","�հ�"};
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
	        
	        // ȭ�� �ϴ�
	        Panel pSouth = new Panel();
	        pSouth.setFont(font);
	        //pSouth.setBackground(new Color(255, 255, 215));
	        pSouth.setBackground(new Color(102, 204, 204));
	        
	        Button bt1 = new Button("�ֹ�");
	        Button bt2 = new Button("�ʱ�ȭ");
	        Button bt3 = new Button("�ݱ�");
	        pSouth.add(bt1);
	        pSouth.add(bt2);
	        pSouth.add(bt3);
	 
	        // �ֹ���ư
	        bt1.addActionListener(new ActionListener() {
	 
	            @Override
	            public void actionPerformed(ActionEvent e) {
	           //     JOptionPane.showMessageDialog(null, ta.getText() + " �ֹ��Ǿ����ϴ�. \n�̿����ּż� �����մϴ�.");
	                for (int i = 0; i < menu.length; i++) {
	                    bt[i].setEnabled(true);
	                    minus[i].setEnabled(false);
	                    plus[i].setEnabled(false);
	                    suja[i].setText("0");
	                //    ta.setText("   ��ǰ��        �ܰ�        ����        �հ�\n\n");
	 
	                }
	            }
	        });
	 
	        // �ʱ�ȭ ��ư
	        bt2.addActionListener(new ActionListener() {
	 
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                for (int i = 0; i < menu.length; i++) {
	                    bt[i].setEnabled(true);
	                    minus[i].setEnabled(false);
	                    plus[i].setEnabled(false);
	                    suja[i].setText("0");
	           //         ta.setText("   ��ǰ��        �ܰ�        ����        �հ�\n\n");
	 
	                }
	            }
	        });
	 
	 
	        //bt3 �ݱ��ư
	        
	        bt3.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
	        });
	 
	 
	        // ������Ʈ
	        frame.add(pNorth, BorderLayout.NORTH);
	        frame.add(table, BorderLayout.CENTER);
	        //frame.add(ta, BorderLayout.CENTER);
	        frame.add(pSouth, BorderLayout.SOUTH);
	        frame.setVisible(true);
	 
	        // �̺�Ʈ��
	        for (int i = 0; i < menu.length; i++) {
	            int j = i;
	 
	            // �ֹ� ��ư �̺�Ʈ
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
	 
	            // "-" ��ư �̺�Ʈ
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
	            
	            // "+" ��ư �̺�Ʈ
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
	            
	            //Ȯ�� ��ư �̺�Ʈ
	            ok[i].addActionListener(new ActionListener() {
	 
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    list = bt[j].getActionCommand();
	               //     ta.append("   " + list + "       " + price[j] + "        " + count + "         " + price[j] * count
	                //            + "��" + "\n");
	                    ok[j].setEnabled(false);
	                }
	            });
	 
	        }
	 
	        // ����
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
