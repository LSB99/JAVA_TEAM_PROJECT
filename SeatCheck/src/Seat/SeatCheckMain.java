package Seat;


/**
 * GUI���� �ϴ� ��κ��� �ϵ��� ó�����ִ� �߾�����Ŭ�����̴�.
 * �ϴ� �� ������ 
 * �������� ����

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

	// �ٽ� �ʵ� : �¼� ��ü���� �����԰� ���ÿ� ������ �����Ѵ�.
	public HashMap<Seat, Socket> clients 
		= new HashMap<Seat, Socket>();
	public Seat[] pcseat = new Seat[50];
	Manage mf;
	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("��Ʈüũ ������ �׽�Ʈ ����\n ");
		new Manage_Fr_Hud();

	}

	// 00.�ٱ��� �����ӿ��� ������������ ���̰� �Ѵ�.
	

	public void mainFrameHUD() {
		mf = new Manage_Fr_Hud();
		Thread host = new HostPcServer();
		host.start();
	}

	// ���� ���� �����Ӽ��� (������ ��)

	// 01.��Ʈ��Ÿ�� �̱��游���
	private static SeatCheckMain instance = new SeatCheckMain();

	public static SeatCheckMain getInstance() {
		System.out.println("������Ʈ��ȣ��");
		return instance;
	}

	public static SeatCheckMain getInstance(String s) {
		System.out.println(s + "���� ������Ʈ��ȣ��");
		return instance;
	}

	private SeatCheckMain() {
		System.out.println("������Ʈ�� : ������Ʈ�� ���� ");
		// ����ȭ���Ѽ� �����尣 �񵿱�ȭ�߻������ʵ���
		Collections.synchronizedMap(clients);
	}

	// 02.���ο� �ڸ��޴� �޼ҵ�
	public void newSeat(int num, String name, Socket socket) {
		pcseat[num] = new Seat(num, name);
		clients.put(pcseat[num], socket);
	}

	// 03.��ǻ������ from HostPcServer
	public void turnOn(int num) {
		System.out.println("������Ʈ��  : �Ͽ½���!");
		mf.pan[num].setBackground(Color.white);
		mf.pan[num].label[1].setText("�ڸ� ����");
		mf.pan[num].turnOn();
		ddaom(num);

	}

	// 04.��ǻ�� ���� from HostPcServer
	public void turnOff(int num) {
		System.out.println("�ڸ���������");
		mf.pan[num].setBackground(Color.gray);
		mf.pan[num].label[0].setText((num + 1) + ". ���ڸ�");
		ddaom(num);
		mf.pan[num].turnOff();
	}

	

	// 07.������ ����ǥ ó�� from HostPcServer
	public void ddaom(int num) {
		mf.pan[num].label[1].setText("");
		mf.pan[num].label[2].setText("");
		mf.pan[num].label[3].setText("");

	}
	

	public void test(int num) {
		System.out.println("������Ʈ�� : " + num);
	}
}