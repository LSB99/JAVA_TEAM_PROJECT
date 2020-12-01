//자리이동
package Seat;

import javax.swing.JFrame;

public class move extends JFrame{
	public move(){
		setTitle("자리이동");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,500);
		setAlwaysOnTop(true);
		setVisible(true);
	}
	public static void main(String[] args){
		new move();
	}
}
