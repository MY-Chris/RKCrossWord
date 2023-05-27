package com.acm.rossword.service;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class StopWatch extends JPanel implements Runnable
{
    JLabel disp;
    //JButton btn;
    boolean stop=false;
    int j,k,l;

    public CrosswordGUI gui;
    public StopWatch()
    {
        disp=new JLabel();
        //btn=new JButton("Start");
        disp.setFont(new Font("Helvetica",Font.PLAIN,20));
        disp.setBackground(Color.cyan);
        disp.setForeground(Color.red);
        //Container c=getContentPane();
        setLayout(new FlowLayout());
        add(disp);
        //add(btn);
        //btn.addActionListener(this);
    }

    public void run()
    {
        for(j=9;j>=0;j--)
        {
            for(k=59;k>=0;k--)
            {
                for(l=100;l>=0;l--)
                {
                    if(stop)
                    {
                        break;
                    }
                    NumberFormat nf = new DecimalFormat("00");
                    disp.setText(nf.format(j)+":"+nf.format(k)+":"+nf.format(l));
                    try{
                        Thread.sleep(10);
                    }catch(Exception e){}
                }
            }
        }
        if (timeUp()) {
            gui.stopGame();
        }
    }

    /*
    public void actionPerformed(ActionEvent ae)
    {
        Thread t=new Thread(this);

        if(ae.getActionCommand().equals("Start"))
        {
            t.start();
            btn.setText("Stop");
        }
        else
        {
            stop=true;
        }
    }
    */

    public void startWatch() {
        Thread t=new Thread(this);
        t.start();
    }

    public void stopWatch() {
        stop = true;
    }

    public boolean timeUp() {
        return j == 0 && k == 0 && l == 0;
    }
}
