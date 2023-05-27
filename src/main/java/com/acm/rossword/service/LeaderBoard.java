package com.acm.rossword.service;

import com.acm.rossword.entity.ScoreEntity;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.LinkedList;

public class LeaderBoard extends JPanel {
    private List<String> usernames = new LinkedList<>();

    private List<Integer> scores = new LinkedList<>();

    private int row = 11;
    private int col = 3;

    JLabel[][] slots = new JLabel[row][col];

    // for test usage only
//    public LeaderBoard() {
//        usernames.add("a");
//        usernames.add("bsd");
//        usernames.add("asdada");
//        usernames.add("ewqeq");
//        scores.add(1);
//        scores.add(213123);
//        scores.add(2131);
//        scores.add(5656);
//        initBoard();
//        fillBoard();
//    }


    public LeaderBoard(List<ScoreEntity> top10) {
        System.out.println("inside leaderboard constructor");
        transform(top10);
        initBoard();
        fillBoard();
    }


    private void initBoard() {
        setBounds(100, 0, 900, 770);
        setLayout(new GridLayout(row, col));
        setBackground(Color.darkGray);
        setVisible(true);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                slots[i][j] = new JLabel("", SwingConstants.CENTER);
                slots[i][j].setSize(300,70);
                slots[i][j].setFont(new Font("Helvetica",Font.PLAIN,30));
                slots[i][j].setBackground(Color.cyan);
                slots[i][j].setForeground(Color.white);
                //lots[i][j].setLayout(new FlowLayout());
                add(slots[i][j]);
            }
        }
        slots[0][0].setText("Rank");
        slots[0][1].setText("Username");
        slots[0][2].setText("Score");
    }

    private void fillBoard() {
        for (int i = 0; i < usernames.size(); i++) {
            int j = i + 1;
            slots[j][0].setText(Integer.valueOf(j).toString());
            slots[j][1].setText(usernames.get(i));
            slots[j][2].setText(scores.get(i).toString());
        }
    }


    private void transform(List<ScoreEntity> top10) {
        for (ScoreEntity user : top10) {
            usernames.add(user.getUserName());
            scores.add(user.getScore());
        }
    }

}
