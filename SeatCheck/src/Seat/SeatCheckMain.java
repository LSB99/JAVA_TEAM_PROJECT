package Seat;


/**
 * GUI에서 하는 대부분의 일들을 처리해주는 중앙집권클래스이다.
 * 하는 일 순서도 
 * 유지보수 일지

 */
import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


//import model.People;
import model.Seat;


public class SeatCheckMain {
	
	Socket socket;
	DataOutputStream out;

	// 핵심 필드 : 좌석 객체모델을 저장함과 동시에 소켓을 저장한다.
	public HashMap<Seat, Socket> clients 
		= new HashMap<Seat, Socket>();
	public Seat[] pcseat = new Seat[50];
	Manage mf;
	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("시트체크 프페임 테스트 시작\n ");
		new Manage_Fr_Hud();

	}

	// 00.바깥의 프레임에서 메인프레임을 보이게 한다.
	

	public void mainFrameHUD() {
		mf = new Manage_Fr_Hud();
		Thread host = new HostPcServer();
		host.start();
	}

	// 메인 관리 프레임선택 (삽질한 것)

	// 01.컨트롤타워 싱글톤만들기
	private static SeatCheckMain instance = new SeatCheckMain();

	public static SeatCheckMain getInstance() {
		System.out.println("브이컨트롤호출");
		return instance;
	}

	public static SeatCheckMain getInstance(String s) {
		System.out.println(s + "에서 브이컨트롤호출");
		return instance;
	}

	private SeatCheckMain() {
		System.out.println("브이컨트롤 : 브이컨트롤 가동 ");
		// 동기화시켜서 쓰레드간 비동기화발생하지않도록
		Collections.synchronizedMap(clients);
	}

	// 02.새로운 자리받는 메소드
	public void newSeat(int num, String name, Socket socket) {
		pcseat[num] = new Seat(num, name);
		clients.put(pcseat[num], socket);
	}

	// 03.컴퓨터켜짐 from HostPcServer
	public void turnOn(int num) {
		System.out.println("브이컨트롤  : 턴온시작!");
		mf.pan[num].setBackground(Color.white);
		mf.pan[num].label[1].setText("자리 켜짐");
		mf.pan[num].turnOn();
		ddaom(num);

	}

	// 04.컴퓨터 꺼짐 from HostPcServer
	public void turnOff(int num) {
		System.out.println("자리꺼짐입장");
		mf.pan[num].setBackground(Color.gray);
		mf.pan[num].label[0].setText((num + 1) + ". 빈자리");
		ddaom(num);
		mf.pan[num].turnOff();
	}

	

	// 07.나머지 따옴표 처리 from HostPcServer
	public void ddaom(int num) {
		mf.pan[num].label[1].setText("");
		mf.pan[num].label[2].setText("");
		mf.pan[num].label[3].setText("");

	}
	

	public void test(int num) {
		System.out.println("브이컨트롤 : " + num);
	}
}