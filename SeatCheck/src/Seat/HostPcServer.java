package Seat;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class HostPcServer extends Thread {
	SeatCheckMain vc = SeatCheckMain.getInstance("ȣ��Ʈ����"); // �̱���ҷ�����;
	ServerReceiver receiver = null;

	// ������ŸƮ
	

	public void run() {

		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("ȣ��Ʈ �ǽ� : " + "PC�� ȣ��Ʈ�� ���۵˴ϴ�");

			// ������ ��� �޾Ƴ��� ������~
			while (true) {
				socket = serverSocket.accept();
				System.out.println("ȣ��Ʈ �ǽ� : " + "[" + socket.getInetAddress()
						+ "]���� �����Ͽ���!");

				receiver = new ServerReceiver(socket);
				receiver.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���� ���ù� Ŭ���� - �� Ŭ������ ������ ���涧���� ��� �����.
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		// �����ڿ����� ���������� �޾Ƽ� ��ǲ�ƿ�ǲ ��Ʈ���� �ϳ� ����� �����Ѵ�~
		ServerReceiver(Socket socket) {
			this.socket = socket;

			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				System.out.println("ȣ��Ʈ �ǽ� : �ξƿ� �����Ϸ�!");
			} catch (IOException e) {
				System.out.println("ȣ��Ʈ �ǽ� : " + "���� ���ù� ���� ����� ����");
			}
		}// ������ ��

		
	}
}