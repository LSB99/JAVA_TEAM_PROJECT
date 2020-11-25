package Seat;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class HostPcServer extends Thread {
	SeatCheckMain vc = SeatCheckMain.getInstance("호스트서버"); // 싱글톤불러오기;
	ServerReceiver receiver = null;

	// 서버스타트
	

	public void run() {

		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("호스트 피시 : " + "PC방 호스트가 시작됩니다");

			// 접속을 계속 받아내는 쓰레드~
			while (true) {
				socket = serverSocket.accept();
				System.out.println("호스트 피시 : " + "[" + socket.getInetAddress()
						+ "]에서 접속하였다!");

				receiver = new ServerReceiver(socket);
				receiver.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 내부 리시버 클래스 - 이 클래스는 연결이 생길때마다 계속 생긴다.
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		// 생성자에서는 서버소켓을 받아서 인풋아웃풋 스트림을 하나 만들고 연결한다~
		ServerReceiver(Socket socket) {
			this.socket = socket;

			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				System.out.println("호스트 피시 : 인아웃 생성완료!");
			} catch (IOException e) {
				System.out.println("호스트 피시 : " + "서버 리시버 소켓 입출력 에러");
			}
		}// 생성자 끝

		
	}
}