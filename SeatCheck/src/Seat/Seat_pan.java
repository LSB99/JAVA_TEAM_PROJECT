package Seat;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;




public class Seat_pan extends Seat_panAb implements ActionListener,
		MouseListener {
	private static final long serialVersionUID = 1L;
	SeatCheckMain vcm = SeatCheckMain.getInstance("��Ʈ��");
	BufferedImage img = null;
	JLayeredPane lpane;
	JPanel panel3;

	public Seat_pan(int i) {
		num = i;
		isChecked = false;
		img("gameOff");
		setLayout(null);

		// ���̷��̾�� �г�
		lpane = new JLayeredPane();
		lpane.setBounds(0, 0, 1600, 900);
		lpane.setLayout(null);
		lpane.setOpaque(false);
		// �̹��� �г�
		JPanel panel = new InnerPanel();
		panel.setBounds(0, 0, 99, 99);
		panel.setOpaque(false);
		// �ȿ� �� ���빰��
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 0, 99, 99);

		int y = 15;
		for (int a = 0; a < 4; a++) {
			if (a == 0)
				label[a] = new JLabel((i + 1) + ". ���ڸ�");
			else
				label[a] = new JLabel("");

			label[a].setBounds(20, y, 80, 15);
			y += 16;
			label[a].setForeground(new Color(36, 205, 198));
			label[a].setFont(new Font("����ǹ��� �ѳ�", 1, 12));
			panel2.add(label[a]);
		}
		panel2.setOpaque(false);

		// üũ�г�
		panel3 = new CheckPanel();
		panel3.setLayout(null);
		panel3.setBounds(0, 0, 99, 99);
		panel3.setOpaque(false);
		// ������ ���̱�
		lpane.add(panel, new Integer(0), 0);
		lpane.add(panel2, new Integer(1), 0);

		add(lpane);
		setVisible(true);

		setOpaque(false);
		setFocusable(true);
		addMouseListener(this);
		/** ���⼭���ʹ� ������ ��ư ����~ */
		pMenu = new JPopupMenu();
		miEnd = new JMenuItem("����");
		miEnd.addActionListener(this);
		miInfo = new JMenuItem("ȸ������");
		miInfo.addActionListener(this);
		miChat = new JMenuItem("�޼���������");
		miChat.addActionListener(this);
		pMenu.add(miEnd);
		pMenu.add(miInfo);
		pMenu.add(miChat);
		// �гο� ���콺 �����ʸ� ���δ�. JPopupMenu�� �̷������� ������ �ؾ� �Ѵ�..
		addMouseListener(new MousePopupListener());
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("��Ʈ �г�");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(99, 144);

		JPanel panel = new Seat_pan(1);
		f.add(panel);

		f.setVisible(true);
	}

	public void img(String s) {
		// �̹��� �޾ƿ��� - turnOn, turnOff, gameOn, gameOff
		try {
			// System.out.println("�̹��� �ҷ��ɴϴ�~");
			img = ImageIO.read(new File("img/" + s + ".png"));
		} catch (IOException e) {
			System.out.println("�̹��� �ҷ����� ����!");
			System.exit(0);
		}
		repaint();
	}

	/** �̺κ��� ���� üũ */
	public void turnOn() {
		img("gameOn");
		isTurned = true;
	}

	public void turnOff() {
		img("gameOff");
		isTurned = false;
	}

	public void checkOn() {
		lpane.add(panel3, new Integer(2), 0);
		this.isChecked = true;
		repaint();
	}

	@Override
	public void checkOff() {
		lpane.remove(panel3);
		this.isChecked = false;
		repaint();
	}

	// �̹����ҷ������г�
	class InnerPanel extends JPanel {
		private static final long serialVersionUID = 1547128190348749556L;

		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(img, 0, 0, null);
		}
	}

	// üũ�г�
	@SuppressWarnings("serial")
	class CheckPanel extends JPanel {
		BufferedImage img_c;

		CheckPanel() {
			try {
				img_c = ImageIO.read(new File("img/check.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(img_c, 0, 0, null);
		}
	}

	/** ���⼭���� �׼�ó�� */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (this.isChecked == false) {
			checkOn();

		} else if (this.isChecked == true) {
			checkOff();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@SuppressWarnings("static-access")
	@Override
	public void mousePressed(MouseEvent me) {
		if (me.getModifiers() == me.BUTTON3_MASK)
			pMenu.show(this, me.getX(), me.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	

	class MousePopupListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			checkPopup(e);
		}

		public void mouseClicked(MouseEvent e) {
			checkPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			checkPopup(e);
		}

		private void checkPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				pMenu.show(Seat_pan.this, e.getX(), e.getY());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}