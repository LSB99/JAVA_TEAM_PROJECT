package net.skhu.gui;

import net.skhu.dto.Client;
import net.skhu.mapper.ClientMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan(basePackages="net.skhu.mapper")
public class SearchApp extends JFrame {

    @Autowired
    ClientMapper clientMapper;

    String[] conditions = {"전체", "이름", "아이디"}; // 조회 조건
    JTextField word;
    JTable table;
    JLabel label;
    int id;
    int index = 0;

    String [] header = { "이름", "나이", "번호", "주소", "아이디"};
    String [][] contents = new String[50][5];
    String [][] result = { { "name", "age", "h.p", "address", "clientId"}, };

    public SearchApp() {
        setTitle("회원 조회");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        JComboBox<String> strCombo = new JComboBox<String>(conditions);
        strCombo.setBounds(10, 10, 80, 30);
        c.add(strCombo);

        label = new JLabel("");
        label.setBounds(50, 70, 200, 30);
        c.add(label);

        word = new JTextField(20);
        word.setBounds(91, 10, 226, 30);
        c.add(word);
        JButton searchBtn = new JButton("조회");
        searchBtn.setBounds(317, 10, 70, 30);
        c.add(searchBtn);
        JButton removeBtn = new JButton("삭제");
        removeBtn.setBounds(388, 10, 70, 30);
        c.add(removeBtn);

        DefaultTableModel model = new DefaultTableModel(contents, header);
        table = new JTable(model);

        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,120,450,300);

        c.add(scrollPane);
        setSize(500, 500);
        setVisible(true);

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() == -1) { // 아무것도 선택하지 않고 버튼을 눌렀을 때
                    return;
                } else {
                    model.removeRow(table.getSelectedRow()); // 선택열 삭제
                    clientMapper.delete(id); // db에서도 삭제
                    label.setText("회원 삭제 완료.");
                    label.setForeground(Color.BLACK);
                }
            }
        });

        searchBtn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (index == 0) { // 전체 조회
                    List<Client> clientList = clientMapper.findAll();

                    int i=0;
                    for (Client c : clientList) {
                        contents[i][0] = c.getName();
                        contents[i][1] = c.getAge();
                        contents[i][2] = c.getPhoneNumber();
                        contents[i][3] = c.getAddress();
                        contents[i][4] = c.getClientId();

                        model.setDataVector(contents, header);
                        i++;
                    }
                    label.setText("");

                }
                if (index == 1) { // 이름으로 조회
                    List<Client> clientNameList = clientMapper.findByName(word.getText());

                    if(clientNameList.size() == 0) {
                        label.setText("존재하지 않는 회원입니다.");
                        label.setForeground(Color.red);
                    }

                    int i=0;
                    for (Client c : clientNameList) {
                        result[i][0] = c.getName();
                        result[i][1] = c.getAge();
                        result[i][2] = c.getPhoneNumber();
                        result[i][3] = c.getAddress();
                        result[i][4] = c.getClientId();

                        id = c.getId();
                        model.setDataVector(result, header);
                        i++;
                    }
                    label.setText("");

                } else if (index == 2) { // 아이디로 조회
                    List<Client> clientIdList = clientMapper.findById(word.getText());

                    int i = 0;
                    for (Client c : clientIdList) {
                        result[i][0] = c.getName();
                        result[i][1] = c.getAge();
                        result[i][2] = c.getPhoneNumber();
                        result[i][3] = c.getAddress();
                        result[i][4] = c.getClientId();

                        id = c.getId();
                        model.setDataVector(result, header);
                        i++;
                    }
                    label.setText("");
                }
            }
        });

        strCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox) e.getSource();
                index = cb.getSelectedIndex();
            }
        });
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SearchApp.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {

            SearchApp ex = ctx.getBean(SearchApp.class);
            ex.setVisible(true);
        });
    }
}