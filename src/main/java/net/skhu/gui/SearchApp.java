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

    String[] conditions = {"이름", "아이디"}; // 조회 조건
    JTextField word;
    JTable table;
    JLabel label;
    int index = 0;

    String [] header = { "이름", "나이", "번호", "주소", "ID"};
    String [][] contents = {
            { "name", "age", "phoneNumber", "address", "clientId"},
    };

    public SearchApp() {
        setTitle("회원 조회");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        JComboBox<String> strCombo = new JComboBox<String>(conditions);
        strCombo.setBounds(50, 10, 100, 30);
        c.add(strCombo);

        label = new JLabel("");
        label.setBounds(60, 70, 200, 30);
        c.add(label);

        word = new JTextField(20);
        word.setBounds(151, 10, 160, 30);
        c.add(word);
        JButton searchBtn = new JButton("조회");
        searchBtn.setBounds(312, 10, 80, 30);
        c.add(searchBtn);

        DefaultTableModel model = new DefaultTableModel(contents, header);
        table = new JTable(model);

        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,120,450,300);

        c.add(scrollPane);
        setSize(500, 500);
        setVisible(true);

        searchBtn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (index == 0) { // 이름으로 조회
                    List<Client> clientNameList = clientMapper.findByName(word.getText());

                    if(clientNameList.size() == 0)
                        label.setText("존재하지 않는 회원입니다.");

                    for (Client c : clientNameList) {
                        for(int i=0; i<clientNameList.size(); i++) {
                            contents[i][0] = c.getName();
                            contents[i][1] = c.getAge();
                            contents[i][2] = c.getPhoneNumber();
                            contents[i][3] = c.getAddress();
                            contents[i][4] = c.getClientId();

                            model.setDataVector(contents, header);
                            label.setText("");
                            word.setText("");
                        }
                    }

                } else if (index == 1) { // 아이디로 조회
                    List<Client> clientIdList = clientMapper.findById(word.getText());

                    if(clientIdList.size() == 0)
                        label.setText("존재하지 않는 회원입니다.");

                    for (Client c : clientIdList) {
                        for(int i=0; i<clientIdList.size(); i++) {
                            contents[i][0] = c.getName();
                            contents[i][1] = c.getAge();
                            contents[i][2] = c.getPhoneNumber();
                            contents[i][3] = c.getAddress();
                            contents[i][4] = c.getClientId();

                            model.setDataVector(contents, header);
                            label.setText("");
                            word.setText("");
                        }
                    }

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
