package example.crosswordpuzzlegenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class CrosswordGUI extends JFrame implements ActionListener {
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
    StopWatch stopWatch = new StopWatch();

    CrosswordGUI() {
        setBounds(100, 200, 1060, 638);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        add(playPanel());
        add(questionPanel());
        setVisible(true);
    }

    CrosswordGUI(String[][] grid) {
        setEasyGrid(grid);
        setBounds(100, 200, 1100, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        add(playPanel());
        add(questionPanel());
        stopWatch.setBounds(0, 0, 1000, 150);
        add(stopWatch);
        stopWatch.startWatch();
        setVisible(true);
    }

    public JPanel playPanel() {


        playPanel.setBounds(0, 150, 600, 600);
        playPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        playPanel.setBackground(Color.darkGray);
        playPanel.setVisible(true);
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

        puzzleSelector.setBounds(0, 0, 240, 34);
        puzzleSelector.setBackground(Color.darkGray);
        puzzleSelector.setForeground(Color.white);
        puzzleSelector.addActionListener(this);

        createBtn.setBounds(320, 0, 80, 34);
        createBtn.setFocusable(false);
        createBtn.setText("Create");
        createBtn.setBackground(Color.darkGray);
        createBtn.setForeground(Color.white);
        createBtn.addActionListener(this);

        checkBtn.setBounds(240, 0, 80, 34);
        checkBtn.setFocusable(false);
        checkBtn.setText("Check");
        checkBtn.setBackground(Color.darkGray);
        checkBtn.setForeground(Color.white);
        checkBtn.addActionListener(this);

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
        acrossScroll.setBounds(20, 250, 380, 200);
        acrossScroll.setLayout(new ScrollPaneLayout());
        acrossScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        acrossScroll.setForeground(Color.darkGray);
        acrossScroll.setBackground(Color.darkGray);
        acrossScroll.setFocusable(false);
        acrossScroll.setBorder(BorderFactory.createEmptyBorder());

        down.setText("Down");
        down.setForeground(Color.white);
        down.setFont(new Font("Arial", Font.BOLD, 18));
        down.setBounds(20, 470, 400, 34);
        down.setVisible(true);

        downHints.setForeground(Color.white);
        downHints.setBackground(Color.darkGray);
        downHints.setFont(new Font("Arial", Font.BOLD, 14));
        downHints.setSize(360, 200);
        downHints.setText("");

        downScroll = new JScrollPane(downHints);
        downScroll.setBounds(20, 510, 380, 200);
        downScroll.setLayout(new ScrollPaneLayout());
        downScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        downScroll.setForeground(Color.darkGray);
        downScroll.setBackground(Color.darkGray);
        downScroll.setFocusable(false);
        downScroll.setBorder(BorderFactory.createEmptyBorder());

        questionPanel.add(puzzleSelector);
        questionPanel.add(createBtn);
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
            acrossHints.setText("* Lets you hear what you are doing on a computer\n" +
                    "* Points and clicks\n");
            downHints.setText("* Allows you to type letters and numbers\n" +
                    "* Lets you see what you are doing on a computer\n" +
                    "* The brain of the computer");
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
        int k = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                try {
                    if (i == cols[k] && j == rows[k]) {
                        if (slots[i][j].getName().equals(slots[i][j].getText())) {
                            slots[i][j].setForeground(new Color(0, 134, 30));
                        } else {
                            slots[i][j].setForeground(Color.red);
                        }
                        k++;
                    }
                } catch (Exception e) {

                }

            }
        }
        if(checkWin(cols,rows)){

            System.out.println("You Won");
        }
    }
    public boolean checkWin(int[] cols, int[] rows){
        for (int k = 0; k < cols.length; k++) {
            int i = cols[k];
            int j = rows[k];
            try {
                if(slots[i][j].getForeground() == Color.red){
                    return false;
                }
            } catch (Exception e) {

            }
        }
        return true;
    }

    public static void main(String[] args) {
        CrosswordPuzzleGenerator generator = new CrosswordPuzzleGenerator();
        new CrosswordGUI(generator.res);
        //new CrosswordGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createBtn) {
            System.out.println("Create new");

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
}
