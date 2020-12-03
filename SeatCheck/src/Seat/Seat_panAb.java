package Seat;


import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
public abstract class Seat_panAb extends JPanel{
	public JLabel[] label = new JLabel[4];
	public boolean isChecked;
	public boolean isLogined;
	public boolean isTurned;
	public int num;//�¼���ȣ�� ȥ�� ������ �ֱ� ���� �ѹ�
	public Color tmp; //��� �÷��� ������ �ִٰ� �ٽ� ���ư��� �ش�. 
	public JPopupMenu pMenu;
	public JMenuItem miEnd, miInfo, miChat;
	public abstract void turnOn();
	public abstract void turnOff();
	public abstract void checkOn();
	public abstract void checkOff();
	public int x,y;
	public String nickname;

}
