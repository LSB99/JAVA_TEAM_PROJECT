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
import java.util.List;

@SpringBootApplication
@MapperScan(basePackages="net.skhu.mapper")
public class SearchApp extends JFrame {

    @Autowired
    ClientMapper clientMapper;

    static JTextField word;
    static JRadioButton name, clientId;

    public SearchApp() {
        setTitle("회원 조회");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(1, 4);
        grid.setVgap(5);
        Container c = getContentPane();
        c.setLayout(grid);

        ButtonGroup g = new ButtonGroup();
        name = new JRadioButton("이름");
        clientId = new JRadioButton("아이디");

        g.add(name);
        g.add(clientId);

        c.add(name);
        c.add(clientId);

        word = new JTextField(20);
        c.add(word);
        JButton searchBtn = new JButton("검색");
        c.add(searchBtn);

        setSize(400, 400);
        setVisible(true);

        searchBtn.addMouseListener(new SearchAdapter());
    }

    class SearchAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if(name.isSelected()) {
                List<Client> clients = clientMapper.findByName(word.getText());
            } else if (clientId.isSelected()) {
                List<Client> clients = clientMapper.findById(word.getText());
            } else {
                List<Client> clients = clientMapper.findAll();
            }
        }
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
