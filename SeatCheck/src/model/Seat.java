package model;

import java.util.Calendar;

import Seat.SeatCheckMain;



/**
 * �¼� ��~ �¼����� �α����ϸ� �̰ɷ� �޴´�.
 */
public class Seat extends Thread {
	
	SeatCheckMain vc = SeatCheckMain.getInstance("��:��Ʈ"); //�̱���ҷ�����
	private int num_seat; // �¼���ȣ
	private String username; // �̸�
	private int money; // ���� �����
	private String time; // ���ð�
	private Calendar date; // ���۽ð� - ��� ����Ҷ� �� ���̴�.
	private boolean isFirst=false;
		
	private boolean isLogin = true; // ���ӿ���
	private boolean isTurn = true;
	private boolean isMember = false;
	
	
	public Seat(int i) {
		this(i, "��ȸ��");
	}

	// ȸ������ �α��� �� ���
	public Seat(int i, String nick) {
		num_seat = i;
		username = nick;
		money = 0;
		time = "00:00";
		

		if (!nick.equals("��ȸ��")) {
			isMember = true;
		}
	}

	// Thread
	public void run() {
		try {
			money=0;
			while (true) {
				// �¼����� 6���� ���������� 100���� �ø���. ��ȸ���� ���
				// ������ ������ ���� 10�ʰ� ���������� 100���� �ø���.
				Thread.sleep(10000);
				money += 100;
				
				
			}

		} catch (InterruptedException e) {
			System.out.println("Seat : �α׾ƿ��� �ѵ��ϳ׿�~!");
			return;
		}
	}

	// Getter&Setter
	public void setNum_seat(int num_seat) {
		this.num_seat = num_seat;
	}

	public int getNum_seat() {
		return num_seat;
	}

	public String getUserame() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
		if(isLogin){
			// �α����� Ʈ���� ��� ���� �ð� �ʱ�ȭ
			date = Calendar.getInstance();
			date.setTimeInMillis(System.currentTimeMillis());
		}
		
	}

	public boolean isMember() {
		return isMember;
	}

	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	
}