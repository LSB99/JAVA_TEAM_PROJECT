package net.skhu.gui;

import net.skhu.dto.Client;
import net.skhu.mapper.ClientMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SpringBootApplication
@MapperScan(basePackages="net.skhu.mapper")
public class JoinApp extends JFrame {

    @Autowired
    ClientMapper clientMapper;

    static JTextField name, age, phoneNumber, address, clientId, password;

    public JoinApp() {
        setTitle("회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(7, 2);
        grid.setVgap(5);
        Container c = getContentPane();
        c.setLayout(grid);

        c.add(new JLabel(" 이름"));
        name = new JTextField(20);
        c.add(name);

        c.add(new JLabel(" 나이"));
        age = new JTextField(20);
        c.add(age);

        c.add(new JLabel(" 전화번호"));
        phoneNumber = new JTextField(50);
        c.add(phoneNumber);

        c.add(new JLabel(" 주소"));
        address = new JTextField(100);
        c.add(address);

        c.add(new JLabel(" 아이디"));
        clientId = new JTextField(30);
        c.add(clientId);

        c.add(new JLabel(" 비밀번호"));
        password = new JPasswordField();
        c.add(password);

        JButton joinBtn = new JButton("회원가입");
        c.add(joinBtn);
        JButton resetBtn = new JButton("초기화");
        c.add(resetBtn);

        setSize(300, 300);
        setVisible(true);

        joinBtn.addMouseListener(new JoinAdapter());
        resetBtn.addMouseListener(new ResetAdapter());
    }

    class JoinAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Client client = new Client();
            client.setName(name.getText());
            client.setAge(age.getText());
            client.setPhoneNumber(phoneNumber.getText());
            client.setAddress(address.getText());
            client.setClientId(clientId.getText());
            client.setPassword(password.getText());
            clientMapper.insert(client);

            JButton btn = (JButton) e.getSource();
            btn.setText("회원가입 완료");
        }
    }

    class ResetAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            name.setText("");
            age.setText("");
            phoneNumber.setText("");
            address.setText("");
            clientId.setText("");
            password.setText("");
        }
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(JoinApp.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {

            JoinApp ex = ctx.getBean(JoinApp.class);
            ex.setVisible(true);
        });

    }
}
