import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumbersGame{

    private int difficulty;
    private int playerScore;
    private String playerName;

    JFrame startScreen;
    JTextArea textArea;
    JPanel bottomPanel;

    //1. get name of player
    //2. difficulty
    //3. Level 1: player inputs a max value. Computer generates a random value between 0 and the number.
    //   The player guesses an integer in the range, and the computer gives hints of higher or lower.
    //   Player repeatedly guesses until the number is found. The number of guesses is stored in the scores lists

    public NumbersGame(){
        startScreen = new JFrame("Numbers Game Menu");
        startScreen.setLocation(100, 100);
        startScreen.setSize(500, 500);
        startScreen.setLayout(new GridLayout(2, 1));
        textArea = new JTextArea();
        textArea.setEditable(false);
        bottomPanel = new JPanel();
        startScreen.add(textArea);
        textArea.setText("\n\n\n\n\n\t\tWelcome to the Numbers Game");
        startScreen.add(bottomPanel);
        bottomPanel.setLayout(new GridLayout(6,1));
        JButton level1 = new JButton("Level 1 Game Start");
        bottomPanel.add(level1);
        JButton level2 = new JButton("Level 2 Game Start");
        bottomPanel.add(level2);
        JButton level3 = new JButton("Level 3 Game Start");
        bottomPanel.add(level3);
        JButton level4 = new JButton("Level 4 Game Start");
        bottomPanel.add(level4);
        JButton leaderboard = new JButton("Leaderboard");
        bottomPanel.add(leaderboard);
        JButton quit = new JButton("Close");
        bottomPanel.add(quit);
        level1.addActionListener(new Level1Listener());
        level2.addActionListener(new Level2Listener());
        level3.addActionListener(new Level3Listener());
        level4.addActionListener(new Level4Listener());
        leaderboard.addActionListener(new LeaderboardListener());
        quit.addActionListener(new CloseListener());

        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setVisible(true);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void addToPlayerList(int difficulty, String playerName, int playerScore){
        //Writes the name in the "Hall of Fame" in game with the score under the correct difficulty.
        //Either saved to a file and read from the file or just per session.
    }

    class Level1Listener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            Level1Game game = new Level1Game();
            startScreen.setVisible(false);
        }
    }

    class Level2Listener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            Level234Game game = new Level234Game(2);
            startScreen.setVisible(false);
        }
    }

    class Level3Listener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            Level234Game game = new Level234Game(3);
            startScreen.setVisible(false);
        }
    }

    class Level4Listener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            Level234Game game = new Level234Game(4);
            startScreen.setVisible(false);
        }
    }

    class LeaderboardListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            Leaderboard leaderBoard = new Leaderboard();
            leaderBoard.vis();
            startScreen.setVisible(false);
        }
    }

    class CloseListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }

}
