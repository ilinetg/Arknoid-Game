import biuoop.GUI;

public class MenuTest {
    public static void main(String[] args) {
        GUI gui = new GUI("Arknoid", 800, 600);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                gui.getKeyboardSensor());
        HighScoresTable score = new HighScoresTable(4);
        ScoreInfo c = new ScoreInfo("hadar", 11);
        ScoreInfo c1 = new ScoreInfo("Moshe", 12);
        ScoreInfo c2 = new ScoreInfo("Gal", 30);
        score.add(c2);
        score.add(c1);
        score.add(c);
        HighScoresAnimation scores = new HighScoresAnimation(score, "h");
        AnimationRunner runner = new AnimationRunner(gui, 60);
        menu.addSelection("h", "Hi scores",
                new ShowHiScoresTask(runner, scores, gui.getKeyboardSensor()));

        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

}
