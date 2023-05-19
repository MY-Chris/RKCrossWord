package example.crosswordpuzzlegenerator;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class ScoreTracker extends JPanel{
    JLabel disp;
    private int score = 0;
    public ScoreTracker()
    {
        disp=new JLabel();
        disp.setFont(new Font("Helvetica",Font.PLAIN,20));
        disp.setBackground(Color.cyan);
        disp.setForeground(Color.red);
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
