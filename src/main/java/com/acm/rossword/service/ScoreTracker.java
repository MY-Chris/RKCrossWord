package com.acm.rossword.service;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ScoreTracker extends JPanel{
    JLabel disp;
    private int score = 0;
    public ScoreTracker()
    {
        disp=new JLabel();
        disp.setFont(new Font("Helvetica",Font.PLAIN,20));
        disp.setBackground(Color.cyan);
        disp.setForeground(Color.green);
        setLayout(new FlowLayout());
        add(disp);
        NumberFormat nf = NumberFormat.getInstance();
        disp.setText("Your score: " + nf.format(score));
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        NumberFormat nf = NumberFormat.getInstance();
        disp.setText("Your score: " + nf.format(score));
    }
}
