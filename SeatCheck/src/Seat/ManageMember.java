package Seat;


import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;




import java.util.*;
import java.awt.event.*;

public class ManageMember {

	private int currentIndex = 0; // �����ε���
	private int endIndex = 0; // �������� �� ���������� �����ϱ� ���� �ε���
	private final int MAX_ROW = 20; // ���̺��� �����͸� ǥ���ϱ� ���� �ִ� �� ��

	// ��ȸ��ư�� �������� Ȯ��
	private boolean doSearch = false;

	// ��񿡼� ���õ� �˻��� ����� ����
	private ArrayList<MemberInfo> list = new ArrayList<MemberInfo>();
	private DefaultTableModel dtm;
	private JTable jt;
	private JTextField idField;
	public static void main(String[] args) {
		new ManageMember();
	}
	// ManageMember������ ����
	@SuppressWarnings("serial")
	public ManageMember() {
		// �÷����� ����
		String[] culumnName = { "���̵�", "��ȭ��ȣ", "���ϸ���", "����" };

		// ������Ʈ ����
		JFrame memberFrame = new JFrame("��������Ȳ");
		dtm = new DefaultTableModel(culumnName, 0) {
			// �������� ���ϰ� �ϴ� �ʵ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		jt = new JTable(dtm);
		JScrollPane panel = new JScrollPane(jt);
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		JLabel idLabel = new JLabel("���̵�");
		JButton leftBtn = new JButton("<<");
		JButton rightBtn = new JButton(">>");
		JButton searchBtn = new JButton("��ȸ");
		JButton deleteBtn = new JButton("����");
		JButton tableSearchBtn = new JButton("�˻�");
		idField = new JTextField(10);

		// ������Ʈ ���պ�
		center.add(panel);
		south.add(leftBtn);
		south.add(rightBtn);
		south.add(searchBtn);
		south.add(idLabel);
		south.add(idField);
		south.add(tableSearchBtn);
		south.add(deleteBtn);

		memberFrame.add(south, "South");
		memberFrame.add(panel, "Center");

		// ��ư �̺�Ʈ ����
		tableSearchBtn.addActionListener(new IdSearchEvent());
		//searchBtn.addActionListener(new SearchEvent());
		leftBtn.addActionListener(new LeftEvent());
		rightBtn.addActionListener(new RightEvent());
		

		// ������ ��ġ ����
		int width = Toolkit.getDefaultToolkit().getScreenSize().width / 3;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height / 7;

		// �������� �⺻���� ��ġ�� ������ ����
		memberFrame.setResizable(false);
		memberFrame.setBounds(width, height, 500, 400);
		memberFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		memberFrame.setVisible(true);

	}

	
	// ��ȸ��ư�� �����ÿ� member���̺��� DB���о�ͼ� ���̺� �����ִ� �̺�Ʈó�� Ŭ��������

	// ���ʹ�ư���� ������ �ѱ涧 �̺�Ʈ ó�� Ŭ����
	private class LeftEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int j = 0;
			if (doSearch == true) {
				if (currentIndex != 0) {
					// �����ͻ���
					resetRow();
					j = ((--currentIndex) * 20);
					for (int i = 0; i < MAX_ROW; i++) {

						String id = list.get(j).getId();
						String tel = list.get(j).getTel();
						String mileage = list.get(j).getMileage();
						String age = list.get(j).getAge();
						String[] str = { id, tel, mileage, age };
						dtm.addRow(str);
						j++;
					}

				} else {
					JOptionPane.showMessageDialog(null, "ù ��° �Դϴ�.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "��ȸ�� �����ϼ���");
			}
		}
	}

	// ���ʹ�ư���� ������ �ѱ涧 �̺�Ʈ ó�� Ŭ���� ����
	// ������ ��ư���� �ѱ涧 �̺�Ʈ Ŭ���� ����
	private class RightEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int j = 0;
			endIndex = setEndIndex(list.size());
			if (doSearch == true) {
				if (currentIndex != endIndex) {
					// �����ͻ���
					resetRow();
					j = ((++currentIndex) * 20);

					Outer: for (int i = 0; i < MAX_ROW; i++) {

						if (j > list.size() - 1) {
							break Outer;
						}
						String id = list.get(j).getId();
						String tel = list.get(j).getTel();
						String mileage = list.get(j).getMileage();
						String age = list.get(j).getAge();
						String[] str = { id, tel, mileage, age };
						dtm.addRow(str);
						j++;
					}

				} else {
					JOptionPane.showMessageDialog(null, "������ �Դϴ�.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "��ȸ�� �����ϼ���");
			}

		}
	}

	// ������ ��ư���� �ѱ涧 �̺�Ʈ Ŭ���� ����
	
	// �����ϰ� �����ϱ� ���� ���̵� �˻��ϰ� �����ϱ� ���� �̺�Ʈ ó�� Ŭ���� ����
	private class IdSearchEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String searchId = idField.getText();
			if (doSearch == true) {
				if (searchId.equals("")) {
					JOptionPane.showMessageDialog(null, "�˻��� ���̵� �Է��ϼ���");
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getId().equals(searchId)) {
							resetRow();
							doSearch=false;
							String id = list.get(i).getId();
							String tel = list.get(i).getTel();
							String mileage = list.get(i).getMileage();
							String age = list.get(i).getAge();
							String[] str = { id, tel, mileage, age };
							dtm.addRow(str);
							return;
						}
					}
					JOptionPane.showMessageDialog(null, "���̵� ���� ���� �ʽ��ϴ�.");

				}
			} else {
				JOptionPane.showMessageDialog(null, "��ȸ ���� ���ּ���.");
			}

		}
	}

	// �����ϰ� �����ϱ� ���� ���̵� �˻��ϰ� �����ϱ� ���� �̺�Ʈ ó�� Ŭ���� ����
	// â�ѱ�⸦ ����� ������ �ε����� �����ϴ� �޼ҵ� ����
	private int setEndIndex(int i) {
		int end = 0;
		end= i / 20;
		return end;
	}

	// â�ѱ�⸦ ����� ������ �ε����� �����ϴ� �޼ҵ� ����

	// ���̺��� ��� �����͸� �����ϴ� �޼ҵ� ����
	private void resetRow() {
		if (dtm.getRowCount() > 0) {
			for (int i = dtm.getRowCount() - 1; i > -1; i--) {
				dtm.removeRow(i);
			}
		}
	}
	// /���̺��� ��� �����͸� �����ϴ� �޼ҵ� ����

}