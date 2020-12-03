package Seat;

/*import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.net.*;
import java.io.*;

//���� ������� �ǽù� �̿����� �̿�ð��� �̿���â�� �����ش�
public class ClientPc {// Ŭ���̾�Ʈ Ŭ���� ����
	
	private String id; // ���� ������� ���̵� ����
	private String pc; // ���� ������� �ǽ� ��ȣ ����
	private JFrame clFrame;
	private JLabel userId;
	private JLabel userPc;
	private JLabel userTime;
	private JLabel userPrice;
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	//private ClientChat chat;
	private Menu menu;
	protected static boolean doClient=true;

	ClientPc(String id, String pc) {// Ŭ���̾�Ʈ �����ڽ���

		this.id = id;
		this.pc = pc;
		clFrame = new JFrame("�̿���");

		// ���� ��ũ������� �޾ƿ´�
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;

		// ǥ�ö�
		userId = new JLabel(id);
		userPc = new JLabel(pc);
		userTime = new JLabel("");
		userPrice = new JLabel("");

		JLabel pc_label = new JLabel("�Ǿ���ȣ:");
		JLabel id_label = new JLabel("�����̵�:");
		JLabel time_label = new JLabel("�̿�ð�:");
		JLabel price_label = new JLabel("�̿���:");

		// ��ư
		JButton chatBtn = new JButton("ä��");
		JButton menuBtn = new JButton("�޴�ǥ");

		// ������Ʈ�� ���� �г� ����
		JPanel panel = new JPanel();

		// ������Ʈ ��ġ��
		pc_label.setBounds(30, 30, 95, 30);
		id_label.setBounds(30, 5, 95, 30);
		time_label.setBounds(30, 55, 95, 30);
		price_label.setBounds(30, 80, 95, 30);
		userId.setBounds(130, 5, 95, 30);
		userPc.setBounds(130, 30, 95, 30);
		userTime.setBounds(130, 55, 95, 30);
		userPrice.setBounds(130, 80, 95, 30);
		chatBtn.setBounds(30, 120, 95, 30);
		menuBtn.setBounds(150, 120, 95, 30);

		// ������Ʈ ���պ�
		panel.add(pc_label);
		panel.add(id_label);
		panel.add(time_label);
		panel.add(userId);
		panel.add(userTime);
		panel.add(userPc);
		panel.add(userPrice);
		panel.add(chatBtn);
		panel.add(menuBtn);
		panel.add(price_label);
		panel.setLayout(null);
		clFrame.add(panel);

		// ��ư �̺�Ʈ ó����
		//chatBtn.addActionListener(new ChatEvent());
		menuBtn.addActionListener(new MenuEvent());

		// ���� ������ ��ġ �� ũ��
		clFrame.setBounds(width - 300, height / 5 - 100, 270, 200);
		clFrame.setResizable(false);

		// ������ â�� ���� �����Ű�� �ȵǹǷ�
		clFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		clFrame.setVisible(true);
		// ���� ���������
		new Thread(new ClientConnector()).start();

	}// Ŭ���̾�Ʈ ����������


	// �޴�ǥ�� �ҷ����� ���� �̺�Ʈ Ŭ���� ����
	private class MenuEvent implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			//Menu menu = new Menu(out,Integer.parseInt(pc));

		}

	}

	// �޴�ǥ�� �ҷ����� ���� �̺�Ʈ Ŭ���� ����
	// ������ ������ ���� Ŭ���̾�Ʈ Ŀ����
	private class ClientConnector implements Runnable {

		@Override
		public void run() {
			try {
				String serverIp = "127.0.0.1";// "172.168.0.80";
				socket = new Socket(InetAddress.getByName(serverIp), 7777);
				System.out.println("���Ἲ��");
				in = new DataInputStream(new BufferedInputStream(
						socket.getInputStream()));
				out = new DataOutputStream(new BufferedOutputStream(
						socket.getOutputStream()));

				int pcNum = Integer.parseInt(pc);
				out.writeInt(pcNum);
				out.writeUTF(id);
				out.writeUTF("�α���");
				out.flush();

				while (true) {
					String str = in.readUTF();
					// �̿��� ó����
					if (str.equals("�������")) {
						Integer money = in.readInt();
						userPrice.setText(money.toString());
						userTime.setText(in.readUTF());
					}
					
					// �α׾ƿ� ó����
					if (str.equals("�α׾ƿ�")) {
						
						socket.close();
					}
				}

			} catch (IOException e) {// ������ ������ �������� â�� ����

				doClient=false;
				clFrame.dispose();

			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}// Ŭ���̾�Ʈ Ŀ��������

}// Ŭ���̾�Ʈ Ŭ���� ����*/