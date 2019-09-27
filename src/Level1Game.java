import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Level1Game extends JFrame {


    JTextArea mainDisplay;
    JPanel bottomPanel;
    int gameNumber;
    String playerName;
    int score;
    JButton theSubButton;
    NumPanel numPanel;

    public Level1Game(){
        super("Level 1 Numbers Game");
        setLocation(100, 100);
        setSize(475, 600);
        setLayout(new GridLayout(2,1));
        TopMenuButtons topMenuButtons = new TopMenuButtons();
        mainDisplay = new JTextArea();
        mainDisplay = new JTextArea();
//        mainDisplay.setLineWrap(true);
//        JScrollPane scroll = new JScrollPane(mainDisplay);
//        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        add(scroll);
        //So, making the main display scrollable breaks the game.
        mainDisplay.setEditable(false);
        bottomPanel = new JPanel();
        numPanel =  new NumPanel();
        score = 0;
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
        mainDisplay.setText("The computer has picked a random value in the range from 0 to a number you provide.\n" +
                            "Your goal is to guess the number in as few guesses as possible.\n");
    }

    public void setUpName(){
        mainDisplay.append("What is your name? Type it below and hit submit.\n");
    }

    public void setUpNum(){
        mainDisplay.setText("What is the max value for this game session?\n");
    }

    public void computersTurn(){
        score++;
        String[] lines = mainDisplay.getText().split("\n");
        String line = lines[lines.length-1];
        int answer;
        try{
            answer = Integer.parseInt(line);
        }
        catch(NumberFormatException x){
            answer = -1;
        }

        if(answer != -1){
            if(answer == gameNumber){
                mainDisplay.setText("\nYOU WON! CONGRATULATIONS\n");
                endGame();
            }
            else if(answer < gameNumber){
                mainDisplay.setText("\nGuess was too low. Keep on going!\n");
            }
            else if(answer > gameNumber){
                mainDisplay.setText("\nGuess was too high. Keep on going!\n");
            }
        }
        else{
            String reset = "";
            for(int i = 0; i < lines.length-1; i++){
                reset = reset + lines[i] + "\n";
            }
            mainDisplay.setText(reset);
            mainDisplay.append("Please try again.\n");
        }
    }

    public void endGame(){
        Leaderboard leaderBoard = new Leaderboard();
        Score gameScore = new Score(playerName, 1, this.score);
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
                mainDisplay.setEditable(false);
                fixSubButton1();
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
                String[] lines = mainDisplay.getText().split("\n");
                String line = lines[lines.length-1];
                int answer;
                try{
                    answer = Integer.parseInt(line);
                }
                catch(NumberFormatException x){
                    answer = -1;
                }
                if(answer != -1){
                    Random random = new Random();
                    int gN = random.nextInt(answer);
                    gameNumber = gN;
                    fixSubButton2();
                    mainDisplay.setText("Input your first guess.\n");
                }
                else{
                    setUpNum();
                    mainDisplay.append("Please try again.\n");
                }
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
