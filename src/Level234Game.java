import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Level234Game extends JFrame{

    JTextArea mainDisplay;
    JPanel bottomPanel;
    char[] gameNumber;
    String playerName;
    int score;
    JButton theSubButton;
    NumPanel numPanel;
    int difficulty;

    public Level234Game(int difficulty){
        super("Level " + difficulty + " Numbers Game");
        this.difficulty = difficulty;
        setLocation(100, 100);
        setSize(475, 600);
        setLayout(new GridLayout(2,1));
        TopMenuButtons topMenuButtons = new TopMenuButtons();
        mainDisplay = new JTextArea();

        mainDisplay.setEditable(false);
        bottomPanel = new JPanel();
        numPanel =  new NumPanel();
        score = 0;
        gameNumber = new char[difficulty];
        add(mainDisplay);
        add(bottomPanel);
        bottomPanel.setLayout(new GridLayout(1,2));
        bottomPanel.add(numPanel);
        bottomPanel.add(topMenuButtons);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainDisplay.setEditable(true);
        startGame();
        setUpName();
    }

    public void setVis(boolean value){
        setVisible(value);
    }

    public void startGame(){
        mainDisplay.setText("This is a GUESSING GAME "+
                            "Clues will be given telling you if you have the correct digits and are in the correct spot\n"+""
                            		+ " So, let's play! and Enjoy the game\n");
    }

    public void setUpName(){
        mainDisplay.append("What is your name? Type it below and hit submit.\n");
    }

    public void setUpNum(){
        mainDisplay.setText("Hit submit to generate your " + difficulty + " random digits\n");
        Random random = new Random();
        String tempString = "";
        for(int i = 0; i < difficulty; i++){
            int temp = random.nextInt(10);
            tempString += Integer.toString(temp);
        }
        gameNumber = tempString.toCharArray();
    }

    public int getRightDigits(String answer){
        char[] copyOfGN = new char[difficulty];
        System.arraycopy(gameNumber, 0, copyOfGN, 0, difficulty);
        char[] temp = answer.toCharArray();
        int count = 0;
        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < difficulty; j++){
                if(temp[i] == copyOfGN[j]){
                    count++;
                    copyOfGN[j] = ' ';
                    break;
                }
            }
        }
        return count;
    }

    public int getRightSlots(String answer){
        int count = 0;
        char[] temp = answer.toCharArray();
        int min = Math.min(temp.length, difficulty);
        for(int i = 0; i < min; i++){
            if(temp[i] == gameNumber[i]){
                count++;
            }
        }
        return count;
    }

    public void computersTurn(){
        score++;
        String[] lines = mainDisplay.getText().split("\n");
        String answer = lines[lines.length-1];
        if(!answer.equals("")){
            int digits = getRightDigits(answer);
            int slots = getRightSlots(answer);
            if(slots == difficulty){
                mainDisplay.setText("\nYOU WON! CONGRATULATIONS\n");
                endGame();
            }
            else{
                mainDisplay.setText("\nThe answer was incorrect. Your clue is (" + digits + ", " + slots + ").\n");
            }
        }
    }

    public void endGame(){
        Leaderboard leaderBoard = new Leaderboard();
        Score gameScore = new Score(playerName, difficulty, this.score);
        leaderBoard.addScoreFromGame(gameScore);
        leaderBoard.processScores();
        mainDisplay.append("\nClick the Leaderboard button below to view your scores.\n");
    }

    class NumPanel extends JPanel {

        public NumPanel() {
            JButton num0 = new JButton("0");
            JButton num1 = new JButton("1");
            JButton num2 = new JButton("2");
            JButton num3 = new JButton("3");
            JButton num4 = new JButton("4");
            JButton num5 = new JButton("5");
            JButton num6 = new JButton("6");
            JButton num7 = new JButton("7");
            JButton num8 = new JButton("8");
            JButton num9 = new JButton("9");
            theSubButton = new JButton("Submit");
            JButton clear = new JButton("C");
            setLayout(new GridLayout(4, 3));
            add(num9);
            add(num8);
            add(num7);
            add(num6);
            add(num5);
            add(num4);
            add(num3);
            add(num2);
            add(num1);
            add(clear);
            add(num0);
            add(theSubButton);
            num0.addActionListener(new ButtonListener());
            num1.addActionListener(new ButtonListener());
            num2.addActionListener(new ButtonListener());
            num3.addActionListener(new ButtonListener());
            num4.addActionListener(new ButtonListener());
            num5.addActionListener(new ButtonListener());
            num6.addActionListener(new ButtonListener());
            num7.addActionListener(new ButtonListener());
            num8.addActionListener(new ButtonListener());
            num9.addActionListener(new ButtonListener());
            clear.addActionListener(new ClearListener());
            theSubButton.addActionListener(new NameListener());
        }

        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                JButton button = (JButton) ae.getSource();
                String text = button.getText();
                mainDisplay.append(text);
            }
        }
        class ClearListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                String[] lines = mainDisplay.getText().split("\n");
                String reset = "";
                for(int i = 0; i < lines.length-1; i++){
                    reset += lines[i] + "\n";
                }
                mainDisplay.setText(reset);
            }
        }

        class NameListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                String[] lines = mainDisplay.getText().split("\n");
                String line = lines[lines.length-1];
                playerName = line;
                fixSubButton1();
                mainDisplay.setEditable(false);
            }
        }

        public void fixSubButton1(){
            numPanel.remove(theSubButton);
            theSubButton = new JButton("Submit");
            theSubButton.addActionListener(new NumberListener());
            numPanel.add(theSubButton);
            setUpNum();
        }

        class NumberListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                fixSubButton2();
                mainDisplay.setText("Input your first guess.\n");
            }
        }

        public void fixSubButton2(){
            numPanel.remove(theSubButton);
            theSubButton = new JButton("Submit");
            theSubButton.addActionListener(new SubmitListener());
            numPanel.add(theSubButton);
        }

        class SubmitListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                computersTurn();
            }
        }
    }

    class TopMenuButtons extends JPanel {

        public TopMenuButtons() {
            JButton backToMain = new JButton("Back to Main Menu");
            JButton leaderBoard = new JButton("Leader Board");
            backToMain.addActionListener(new BTMListener());
            leaderBoard.addActionListener(new LBListener());
            setLayout(new GridLayout(2,1));
            add(backToMain);
            add(leaderBoard);
        }

        class BTMListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                NumbersGame numbersGame = new NumbersGame();
                setVis(false);
            }
        }

        class LBListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                Leaderboard leaderBoard = new Leaderboard();
                leaderBoard.vis();
                setVis(false);
            }
        }
    }
}
