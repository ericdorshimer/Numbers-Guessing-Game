
public class Score {
    private String name;
    private int difficulty;
    private int points;

    public Score(String name, int difficulty, int points) {
        this.name = name;
        this.difficulty = difficulty;
        this.points = points;
    }
    public Score(String line) {
        //line = "Name,difficulty,points"
        String[] temp = line.split(",");
        this.name = temp[0];
        this.difficulty = Integer.parseInt(temp[1]);
        this.points = Integer.parseInt(temp[2]);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String toString() {
        String scoreString = (this.name + "," + Integer.toString(this.difficulty) + ","+ Integer.toString(this.points));
        return scoreString;
    }

}
