import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Leaderboard extends JFrame{

    File file;
    JTextArea mainScreen;
    static private ArrayList<Score> tempLB;
    static private ArrayList<Score> level1Score;
    static private ArrayList<Score> level2Score;
    static private ArrayList<Score> level3Score;
    static private ArrayList<Score> level4Score;

    public Leaderboard(){
        super("Leader Board");
        setLocation(100, 100);
        setSize(300, 600);
        setLayout(new GridLayout(2,1));
        mainScreen = new JTextArea();
        mainScreen.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(mainScreen);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll);
        JButton backToMain = new JButton("Back to Main Menu");
        backToMain.addActionListener(new BTMListener());
        add(backToMain);
        tempLB = new ArrayList<>(); 
        level1Score = new ArrayList<>();
        level2Score = new ArrayList<>();
        level3Score = new ArrayList<>();
        level4Score = new ArrayList<>();
        file = new File("C:Leaderboard.txt");


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class BTMListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            NumbersGame numbersGame = new NumbersGame();
            unvis();
        }
    }

    public void vis(){
        setVisible(true);
        processScores();
    }

    public void unvis(){
        setVisible(false);
    }

    public void processScores(){
        for(Score s : tempLB){
            addScore(s);
            try{
                addtoLBFile(file, s);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        try{
            String[] temp = getStrings(this.file);
            for(String s : temp){
                addScore(new Score(s));
            }
            sortScoreList(level1Score);
            sortScoreList(level2Score);
            sortScoreList(level3Score);
            sortScoreList(level4Score);
            mainScreen.append("Level 1 Scores\n");
            for(Score l1 : level1Score){
                mainScreen.append(l1.toString() + "\n");
            }
            mainScreen.append("Level 2 Scores\n");
            for(Score l2 : level2Score){
                mainScreen.append(l2.toString() + "\n");
            }
            mainScreen.append("Level 3 Scores\n");
            for(Score l3 : level3Score){
                mainScreen.append(l3.toString() + "\n");
            }
            mainScreen.append("Level 4 Scores\n");
            for(Score l4 : level4Score){
                mainScreen.append(l4.toString() + "\n");
            }
        }
        catch (FileNotFoundException e){
            mainScreen.setText("Error - Leaderboard file not found");
        }
    }

    public void addScoreFromGame(Score sc){
        tempLB.add(sc);
    }

    public void addScore(Score sc) {
        if(sc.getDifficulty() == 1) {
            this.level1Score.add(sc);
        }
        else if(sc.getDifficulty() == 2) {
            this.level2Score.add(sc);
        }
        else if(sc.getDifficulty() == 3) {
            this.level3Score.add(sc);
        }
        else {
            this.level4Score.add(sc);
        }
    }

    public void sortScoreList(ArrayList<Score> scList) {
        for (int i = 0; i < scList.size(); i++) {
            int x = scList.get(i).getPoints();
            for(int j = i + 1; j < scList.size(); j++) {
                int y = scList.get(j).getPoints();
                if(x > y) {
                    Score temp = scList.get(i);
                    scList.set(i, scList.get(j));
                    scList.set(j, temp);
                }
            }
        }
    }

    public static int getLineCount(File file) throws FileNotFoundException {
        int count = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            count ++;
            scanner.nextLine();
        }
        return count;
    }

    public static String[] getStrings(File file) throws FileNotFoundException{
        int lineCount = getLineCount(file);
        String[] input = new String[lineCount];
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine()){
            input[i] = scanner.nextLine();
            i++;
        }
        return input;
    }

    public static void addtoLBFile(File file, Score score) throws IOException {
        FileWriter write = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(write);
        printWriter.println(score.toString());
        printWriter.close();
    }

}
