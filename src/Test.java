import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        HighScoresTable scores = new HighScoresTable(4);
        HighScoresTable scoresLoad = new HighScoresTable(2);
        ScoreInfo c = new ScoreInfo("hadar", 11);
        ScoreInfo c1 = new ScoreInfo("Moshe", 12);
        ScoreInfo c2 = new ScoreInfo("Gal", 30);
        scores.add(c2);
        scores.add(c1);
        scores.add(c);
        try {
            scores.save(new File("test.txt"));
            scoresLoad.load(new File("test.txt"));
        } catch (Exception e) {
            System.out.println("error");
        }
        System.out.println(scoresLoad.getHighScores().get(0).getName());
        System.out.println(scoresLoad.getHighScores().get(1).getName());

    }
}