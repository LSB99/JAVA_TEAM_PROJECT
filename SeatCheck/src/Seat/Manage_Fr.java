package Seat;

/**
 * ������ ȭ���� ���� �г��� �� ���� ȭ��..
 * �¼��г��� panel_seat
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class Manage_Fr extends Manage implements ActionListener{
	private static final long serialVersionUID = 1L;
	JPanel panel;
	public JPanel panel_seat;
	public JLabel label_seat;
	public JTextArea ta = new JTextArea(20, 40);
	JButton bt1, bt2, bt3, bt4, bt5, bt7, bt6, bt8;
	SeatCheckMain vcm = SeatCheckMain.getInstance("�Ŵ���������");
	public Manage_Fr() {
		// ������ �ʱ� ����
		setSize(1600, 900);
		setTitle("���ļ� �Ǿ��濡 ���� ���� ȯ���մϴ�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setBounds(0, 0, 1600, 900);
		panel.setLayout(null);

		// �׺���̼� �г�������Ÿ��
		JPanel navi = new JPanel();
		navi.setLayout(new GridLayout(2, 4));
		navi.setBounds(200, 56, 948, 77);
		bt1 = new JButton("���� ȭ��");
		bt2 = new JButton("ȸ�� ����");
		bt3 = new JButton("��� ����");
		bt4 = new JButton("�Ż� ����");
		bt5 = new JButton("����������");
		bt6 = new JButton("���ô�ü���");
		bt7 = new JButton("����");
		bt8 = new JButton("����");
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		bt6.addActionListener(this);
		bt7.addActionListener(this);
		bt8.addActionListener(this);
		
		
		navi.add(bt1);
		navi.add(bt2);
		navi.add(bt3);
		navi.add(bt4);
		navi.add(bt5);
		navi.add(bt6);
		navi.add(bt7);
		navi.add(bt8);
		
		ta.append("�ϴ��� ���Ⱑ �޽��� �޴� â�Դϴ�\n");
		ta.setBounds(200 + 950, 56, 250, 77);
		panel.add(navi);
		panel.add(ta);
		// �¼� �г�
		JPanel seat = new JPanel();
		seat.setBackground(Color.gray);
		seat.setBounds(223, 183, 1440 - 223, 813 - 183); // 1440 813
		seat.setLayout(new GridLayout(5, 10));

		pan = new Seat_pan[50];
		for (int a = 0; a < 50; a++) {
			pan[a] = new Seat_pan(a);
			seat.add(pan[a]);
		}

		panel.add(seat);
		// �гε� ��� �߰��ϰ� ������!
		add(panel);
		setVisible(true);

	}
	public static void main(String args[]) {
		HostPcServer host = new HostPcServer();
		host.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt6){
			
		}else if(e.getSource()==bt1){
			
		}else if(e.getSource()==bt2){
			//ȸ������
			new ManageMember();
		}else if(e.getSource()==bt5){
			
		}else if(e.getSource()==bt7){
			
		}else if(e.getSource()==bt8){
			
		}
	}
}