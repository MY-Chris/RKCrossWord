package com.acm.rossword.service;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class HintsBoard extends JPanel {
    String across = "";
    String down = "";
    JLabel acrosshints = new JLabel();
    JLabel downhints = new JLabel();
    public HintsBoard(String across, String down) {
        this.across = across;
        this.down = down;
    }

    private void initTextArea() {
        acrosshints.setText(across);
        downhints.setText(down);
        acrosshints.setBackground(Color.green);
        acrosshints.setBounds(0, 0, 200, 200);
        downhints.setBounds(300, 0, 200 ,200);
        downhints.setBackground(Color.green);
        add(acrosshints);
        add(downhints);
        setVisible(true);
    }
}
