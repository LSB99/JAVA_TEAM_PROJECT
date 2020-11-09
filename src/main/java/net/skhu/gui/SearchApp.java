package net.skhu.gui;

import javax.swing.*;
import java.awt.*;

public class SearchApp extends JFrame {

    static JTextField word;

    // 회원 조회 결과 카운트 값으로 for문 써서 출력하기

    public SearchApp() {
        setTitle("회원 조회");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(1, 2);
        grid.setVgap(5);
        Container c = getContentPane();
        c.setLayout(grid);

        word = new JTextField(20);
        c.add(word);
        JButton joinBtn = new JButton("검색");
        c.add(joinBtn);

        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SearchApp();
    }
}
