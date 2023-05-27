package com.acm.rossword.service;

import com.acm.rossword.entity.ScoreEntity;
import com.acm.rossword.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


@Component
public class CrosswordGUI extends JFrame implements ActionListener {
    LinkedHashMap<String,String> mapHorizontal;
    LinkedHashMap<String,String> mapVertical;
    ScoreRepository scoreRepo  ;
    private final static String EMPTY_CELL = "_ ";
    int GRID_SIZE = 10;
    int[] easyCols = {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 9};
    int[] easyRows = {6, 6, 1, 2, 3, 4, 5, 6, 7, 8, 6, 1, 5, 6, 7, 1, 6, 1, 6, 1, 2, 3, 4, 5, 6, 1, 6, 6};
    String[] easyChars = {"H", "E", "K", "E", "Y", "B", "O", "A", "R", "D", "D", "M", "C", "P", "U", "O", "H", "U", "O", "S", "C", "R", "E", "E", "N", "E", "E", "S"};

    int[] midCols = {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 9, 9};
    int[] midRows = {6, 6, 1, 2, 3, 4, 5, 6, 7, 8, 6, 1, 5, 6, 7, 1, 6, 1, 6, 1, 2, 3, 4, 5, 6, 1, 6, 6, 7};
    String[] midChars = {"H", "E", "K", "E", "Y", "B", "O", "A", "R", "D", "D", "M", "C", "P", "U", "O", "H", "U", "O", "S", "C", "R", "E", "E", "N", "E", "E", "S"};

    int[] hardCols = {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 9, 9, 9};
    int[] hardRows = {6, 6, 1, 2, 3, 4, 5, 6, 7, 8, 6, 1, 5, 6, 7, 1, 6, 1, 6, 1, 2, 3, 4, 5, 6, 1, 6, 6, 7, 8};
    String[] hardChars = {"H", "E", "K", "E", "Y", "B", "O", "A", "R", "D", "D", "M", "C", "P", "U", "O", "H", "U", "O", "S", "C", "R", "E", "E", "N", "E", "E", "S"};

    int col = 10;
    int row = 10;
    String[] puzzles = {"Easy", "Medium", "Hard"};
    JTextField[][] slots = new JTextField[col][row];
    JPanel slotHolder;
    JLabel slotIndex;
    JPanel playPanel = new JPanel();

    JPanel questionPanel = new JPanel();
    JLabel across = new JLabel();
    JLabel down = new JLabel();
    JTextArea acrossHints = new JTextArea();
    JScrollPane acrossScroll;
    JTextArea downHints = new JTextArea();
    JScrollPane downScroll;
    JComboBox<String> puzzleSelector = new JComboBox<String>(puzzles);
    JButton createBtn = new JButton();
    JButton checkBtn = new JButton();
    JButton stopBtn = new JButton();
    StopWatch stopWatch = new StopWatch();

    ScoreTracker scoreTracker = new ScoreTracker();
    String grid[][] ;
    Container pane = this.getContentPane();

    CrosswordGUI() {
//        setBounds(100, 200, 1060, 638);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
//        setLayout(null);
//        add(playPanel());
//        add(questionPanel());
//        setVisible(true);

    }

    public CrosswordGUI(String[][] grid, ScoreRepository scoreRepo) {
        this.grid = grid;

        setEasyGrid(grid);
        setBounds(100, 200, 1100, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        pane.add(playPanel());
        pane.add(questionPanel());
        stopWatch.setBounds(0, 0, 100, 150);
        pane.add(stopWatch);
        stopWatch.gui = this;
        stopWatch.startWatch();
        scoreTracker.setBounds(200, 0, 200, 150);
        pane.add(scoreTracker);
        setVisible(true);
        this.scoreRepo = scoreRepo;
        this.scoreRepo = scoreRepo;
    }

    //called by spring boot
    @Autowired
    public CrosswordGUI(ScoreRepository scoreRepo) {


        this.scoreRepo = scoreRepo;

    }

    public JPanel playPanel() {

        playPanel.setBounds(0, 150, 600, 600);
        playPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        playPanel.setBackground(Color.darkGray);
        playPanel.setVisible(true);
//        playPanel.s(true);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                slots[i][j] = new JTextField();
                slots[i][j].setSize(60, 60);
                slots[i][j].setEditable(false);
                slots[i][j].setFocusable(false);
                slots[i][j].setForeground(Color.darkGray);
                slots[i][j].setHorizontalAlignment(JTextField.CENTER);
                slots[i][j].setBackground(Color.darkGray);
                slots[i][j].setFont(new Font("Arial", Font.BOLD, 18));
                slots[i][j].addActionListener(this);
                slotHolder = new JPanel();
                slotIndex = new JLabel();
                slotIndex.setText(" " + i + "/" + j);
                slotIndex.setFont(new Font("Arial", Font.BOLD, 10));
                slotIndex.setForeground(Color.lightGray);
                slotIndex.setSize(30, 20);
                slotHolder.setLayout(null);
                slotHolder.add(slotIndex);
                slotHolder.add(slots[i][j]);
                playPanel.add(slotHolder);
            }
        }
        return playPanel;
    }

    public JPanel questionPanel() {

        questionPanel.setBounds(620, 150, 400, 600);
        questionPanel.setLayout(null);
        questionPanel.setBackground(Color.darkGray);

        puzzleSelector.setBounds(0, 0, 160, 34);
        puzzleSelector.setBackground(Color.darkGray);
        puzzleSelector.setForeground(Color.white);
        puzzleSelector.addActionListener(this);

        createBtn.setBounds(240, 0, 80, 34);
        createBtn.setFocusable(false);
        createBtn.setText("Hints");
        createBtn.setBackground(Color.white);
        createBtn.setForeground(Color.blue);
        createBtn.addActionListener(this);

        checkBtn.setBounds(160, 0, 80, 34);
        checkBtn.setFocusable(false);
        checkBtn.setText("Check");
        checkBtn.setBackground(Color.white);
        checkBtn.setForeground(Color.blue);
        checkBtn.addActionListener(this);

        stopBtn.setBounds(320, 0, 80, 34);
        stopBtn.setFocusable(false);
        stopBtn.setText("Stop");
        stopBtn.setBackground(Color.white);
        stopBtn.setForeground(Color.blue);
        stopBtn.addActionListener(this);

        across.setText("Across");
        across.setForeground(Color.white);
        across.setFont(new Font("Arial", Font.BOLD, 18));
        across.setBounds(20, 60, 400, 34);
        across.setVisible(true);

        acrossHints.setForeground(Color.white);
        acrossHints.setBackground(Color.darkGray);
        acrossHints.setFont(new Font("Arial", Font.BOLD, 14));
        acrossHints.setSize(360, 200);
        acrossHints.setText("");

        acrossScroll = new JScrollPane(acrossHints);
        acrossScroll.setBounds(20, 100, 380, 200);
        acrossScroll.setLayout(new ScrollPaneLayout());
        acrossScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        acrossScroll.setForeground(Color.darkGray);
        acrossScroll.setBackground(Color.darkGray);
        acrossScroll.setFocusable(false);
        acrossScroll.setBorder(BorderFactory.createEmptyBorder());

        down.setText("Down");
        down.setForeground(Color.white);
        down.setFont(new Font("Arial", Font.BOLD, 18));
        down.setBounds(20, 310, 400, 34);
        down.setVisible(true);

        downHints.setForeground(Color.white);
        downHints.setBackground(Color.darkGray);
        downHints.setFont(new Font("Arial", Font.BOLD, 14));
        downHints.setSize(360, 200);
        downHints.setText("");

        downScroll = new JScrollPane(downHints);
        downScroll.setBounds(20, 350, 380, 200);
        downScroll.setLayout(new ScrollPaneLayout());
        downScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        downScroll.setForeground(Color.darkGray);
        downScroll.setBackground(Color.darkGray);
        downScroll.setFocusable(false);
        downScroll.setBorder(BorderFactory.createEmptyBorder());

        questionPanel.add(puzzleSelector);
        questionPanel.add(createBtn);
        questionPanel.add(stopBtn);
        questionPanel.add(checkBtn);
        questionPanel.add(across);
        questionPanel.add(down);
        questionPanel.add(acrossScroll);
        questionPanel.add(downScroll);
        questionPanel.setVisible(true);

        return questionPanel;
    }

    public void initializeGame() {

        if (puzzleSelector.getSelectedIndex() == 0) {
            initializeSlot(easyCols, easyRows, easyChars);

        } else if (puzzleSelector.getSelectedIndex() == 1) {
            initializeSlot(midCols, midRows, midChars);
        } else {
            initializeSlot(hardCols, hardRows, hardChars);
        }
    }

    public void initializeSlot(int[] cols, int[] rows, String[] chars) {
        int k = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                try {
                    if (i == cols[k] && j == rows[k]) {
                        slots[i][j].setBackground(Color.white);
                        slots[i][j].setEditable(true);
                        slots[i][j].setFocusable(true);
                        slots[i][j].setName(chars[k]);
                        k++;
                    }
                } catch (Exception e) {

                }

            }
        }
    }

    public void checkWords(int[] cols, int[] rows, String[] chars) {
        Color green = new Color(0, 134, 30);
        for (int k = 0; k < cols.length; k++) {
            int i = cols[k];
            int j = rows[k];
            try {
                String text = slots[i][j].getText();
                if (slots[i][j].getName().equals(text)) {
                    if (!slots[i][j].getForeground().equals(green)) {
                        slots[i][j].setForeground(green);
                        // add points
                        scoreTracker.setScore(1 + scoreTracker.getScore());
                    }
                } else {
                    if (slots[i][j].getForeground().equals(green)) {
                        // deduct points
                        scoreTracker.setScore(scoreTracker.getScore() - 1);
                    }
                    if (text != null && !text.equals("")) {
                        slots[i][j].setForeground(Color.red);
                    } else {
                        slots[i][j].setForeground(Color.darkGray);
                    }
                }
            } catch (Exception e) {

            }
        }
        if(checkWin(cols,rows)){
            System.out.println("You Won before stopping the game");
            stopGame();
            System.out.println("You Won");
        }
    }
    public boolean checkWin(int[] cols, int[] rows){
        Color green = new Color(0, 134, 30);
        for (int k = 0; k < cols.length; k++) {
            int i = cols[k];
            int j = rows[k];
            try {
                if(slots[i][j].getForeground() != green){
                    return false;
                }
            } catch (Exception e) {

            }
        }
        return true;
    }

    public void temp() throws Exception{
        List<String> horizontalWords = WordExtraction.extractWordsHorizontally(grid);
        System.out.println("words of crosswords are " +horizontalWords );
        mapHorizontal = WordMeaning.extractMeaning(horizontalWords);
//        System.out.println("hashmap is "+ map);

        for (Map.Entry<String,String> temp : mapHorizontal.entrySet())
        {
            System.out.println("key is " + temp.getKey() + " value is   " + temp.getValue());
        }

        List<String> verticalWords = WordExtraction.extractWordsVertically(grid);
        System.out.println("words of crosswords are " +verticalWords );
        mapVertical = WordMeaning.extractMeaning(verticalWords);
//        System.out.println("hashmap is "+ map);
        for (Map.Entry<String,String> temp : mapVertical.entrySet())
        {
            System.out.println("key is " + temp.getKey() + " value is   " + temp.getValue());
        }
//        WordDefinition.extractDefintion(horizontalWords);
//        initializeGame();
//        setHintsDisplay(true);
        setHintsDisplay();
    }

    public void setHintsDisplay() {

        String across = "Words across are the words going across and in order number from top to bottom \n";
        int i = 1;
        for ( Map.Entry<String,String> temp: mapHorizontal.entrySet())
        {
            if (temp.getValue().length() < 10) {
                across += i++ + ". " + "FIGURE OUT THIS WORD BY YOURSELF :)\n";
            } else {
                across += i++ + ". " + temp.getValue().substring(9) + "\n";
            }

        }
        acrossHints.setText(across);

        String down = "Words down are the words going down and in order number from left to right \n";
        i = 1;
        for ( Map.Entry<String,String> temp: mapVertical.entrySet())
        {
            if (temp.getValue().length() < 10) {
                down += i++ + ". " + "FIGURE OUT THIS WORD BY YOURSELF :)\n";
            } else {
                down += i++ + ". " + temp.getValue().substring(9) + "\n";
            }
        }
        downHints.setText(down);
    }

//    public void temp(ScoreRepository scoreRepo) throws Exception{
//
//
//
//        List<String> horizontalWords = WordExtraction.extractWordsHorizontally(generator.res);
//        System.out.println("words of crosswords are " +horizontalWords );
//        mapHorizontal = WordMeaning.extractMeaning(horizontalWords);
////        System.out.println("hashmap is "+ map);
//
//        for (Map.Entry<String,String> temp : mapHorizontal.entrySet())
//        {
//            System.out.println("key is " + temp.getKey() + " value is   " + temp.getValue());
//        }
//
//        List<String> verticalWords = WordExtraction.extractWordsVertically(generator.res);
//        System.out.println("words of crosswords are " +verticalWords );
//        mapVertical = WordMeaning.extractMeaning(verticalWords);
////        System.out.println("hashmap is "+ map);
//        for (Map.Entry<String,String> temp : mapVertical.entrySet())
//        {
//            System.out.println("key is " + temp.getKey() + " value is   " + temp.getValue());
//        }
////        WordDefinition.extractDefintion(horizontalWords);
////        initializeGame();
//        System.out.println("before setting hints display");
//        String across = setHintsDisplay(false);
//        String down =  setHintsDisplay(true);
////        setHintsDisplay(true);
//
//        pane.add(new HintsBoard(across, down));
//    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        if (e.getSource() == createBtn) {
            System.out.println("Create new");
            try {
                temp();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
//            stopGame();
//            setHintsDisplay();
        }
        if (e.getSource() == checkBtn) {
            if (puzzleSelector.getSelectedIndex() == 0) {
                // easy
                checkWords(easyCols, easyRows, easyChars);
            } else if (puzzleSelector.getSelectedIndex() == 1) {
                // medium
                checkWords(midCols, midRows, midChars);
            } else {
                // hard
                checkWords(midCols, midRows, midChars);
            }
        }
        if ( e.getSource() == stopBtn)
        {
            stopGame();
        }
        initializeGame();
    }

    private void setEasyGrid(String[][] grid) {
        if (grid == null) {
            return;
        }
        List<Integer> col = new LinkedList<Integer>();
        List<Integer> row = new LinkedList<Integer>();
        List<String> chars = new LinkedList<String>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!EMPTY_CELL.equals(grid[i][j])) {
                    col.add(i);
                    row.add(j);
                    chars.add(grid[i][j].trim());
                }
            }
        }
        easyCols = col.stream().mapToInt(i -> i).toArray();
        easyRows = row.stream().mapToInt(i -> i).toArray();
        easyChars = chars.stream().toArray(String[] :: new);
        GRID_SIZE = grid.length;
    }

    public void stopGame() {

        int score = scoreTracker.getScore();

        // show leaderboard

        // pop out window show score
        clearScreen();
        int lowest = showLeaderBoard();
        JOptionPane.showMessageDialog(null, "Game ended, your score is " + score);
        System.out.println("game ended, your score is " + score);

        //  if score is bigger than lowest in top 10, let user input username
        if (score > lowest) {
            // present user input component - return username
            String username = JOptionPane.showInputDialog("High Score! What is your name?");
//            JOptionPane.
            System.out.println("username is " + username);
            // call a function to input the username and score into database

            System.out.println("score repo is  ---" + scoreRepo);

           ScoreEntity st = new ScoreEntity(username,score);
           scoreRepo.save(st);

              System.out.println("username is " + username);
            // show the modified leaderboard
           clearScreen();
           showLeaderBoard();
        }
    }

    private void clearScreen() {
        pane.removeAll();
        pane.revalidate();
        pane.repaint();
    }

    private int showLeaderBoard() {


        // query database - to return the top 10
        List<ScoreEntity> entries =scoreRepo.findAll();

        Collections.sort(entries, (a,b) -> b.getScore() - a.getScore());

        List<ScoreEntity> top10 = entries.subList(0,Math.min(entries.size(),10) );
        pane.add(new LeaderBoard(top10));
        if ( top10.size() <10)
        {
            return 0 ;
        }


        int min = top10.stream().min(Comparator.comparingInt(ScoreEntity::getScore)).get().getScore();


        return min; // return the smallest score in the top 10
    }

//    public String setHintsDisplay(boolean b) {
//        System.out.println("msp size is "  + mapHorizontal);
//
//        if ( true)
//        {
//            String across = "";
//            for ( Map.Entry<String,String> temp: mapHorizontal.entrySet())
//            {
//                System.out.println("across is " + across);
//                across += temp.getValue() + "\n";
//            }
//            return across;
//        }
//        else{
//            String down = "";
//            for ( Map.Entry<String,String> temp: mapVertical.entrySet())
//            {
//                down += temp.getValue() + "\n";
//            }
//            downHints.setText(down);
//            return down;
//        }


}
