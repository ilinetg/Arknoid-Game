import java.io.File;
import java.util.List;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * GameFlow class.
 * @author gal
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private GUI gui;
    private Counter lives;
    private int numberOfLives;
    private Counter score;
    private ScoreIndicator scores;
    private LivesIndicator life;
    private HighScoresTable table;

    /**
     * constructor.
     * @param ar
     *            AnimationRunner.
     * @param gui
     *            the game gui.
     * @param lives
     *            number of lives the player has.
     */
    public GameFlow(AnimationRunner ar, GUI gui, int lives) {
        this.runner = ar;
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.numberOfLives = lives;
        this.score = new Counter();
        this.scores = new ScoreIndicator(this.score);
        this.lives = new Counter();
        this.lives.increase(lives);
        this.life = new LivesIndicator(this.lives);
    }

    /**
     * this method runs all the game levels.
     * @param levels
     *            list of all the levels we can play at.
     */
    public void runLevels(List<LevelInformation> levels) {
        int number = this.numberOfLives;
        this.lives.setValue(number);
        this.life = new LivesIndicator(this.lives);
        File tsableResult = new File("highScores.txt");
        if (tsableResult.exists()) {
            this.table = HighScoresTable.loadFromFile(tsableResult);
        } else {
            this.table = new HighScoresTable(5);
        }
        try {
            this.table.save(tsableResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (LevelInformation levelInfo : levels) {
            ScoreTrackingListener scoring = new ScoreTrackingListener(
                    this.score);
            GameLevel level = new GameLevel(levelInfo, this.score, scoring,
                    this.scores, this.life, this.gui);
            level.initialize();

            // while player has more blocks and player has more lives
            while ((level.numOfBlockRemain() > 0) && (level.numOfLives() > 0)) {
                level.playOneTurn();
                this.numberOfLives = level.numOfLives();
            }
            // no more lives and player lost
            if (level.numOfLives() == 0) {
                String losse = "Game Over. Your score is "
                        + Integer.toString(this.score.getValue());
                EndGame end = new EndGame(losse);
                KeyPressStoppableAnimation keyPressStop = new KeyPressStoppableAnimation(
                        this.keyboard, KeyboardSensor.SPACE_KEY, end);
                this.runner.run(keyPressStop);
                // show the high scores
                this.doHighScore();
                // this.gui.close();
                break;
            }
        }
        // if player made all levels
        if (this.numberOfLives != 0) {
            String win = "You Win! Your score is "
                    + Integer.toString(this.score.getValue());
            this.doHighScore();
            EndGame end = new EndGame(win);
            KeyPressStoppableAnimation keyPressStop = new KeyPressStoppableAnimation(
                    this.keyboard, KeyboardSensor.SPACE_KEY, end);
            this.runner.run(keyPressStop);
            // this.gui.close();
        }
        this.numberOfLives = number;
    }

    /**
     * this method updates the highScore table if the score is bigger then on of
     * the other 5 scores that already exist, if there are less than 5 scores it
     * will add it too.
     */
    private void doHighScore() {
        table = new HighScoresTable(5);
        HighScoresTable tmp = new HighScoresTable(5);
        EndGame end;
        HighScoresAnimation animation = new HighScoresAnimation(this.table,
                KeyboardSensor.SPACE_KEY);
        File tsableResult = new File("highScores.txt");
        try {
            tmp.load(tsableResult);
            tmp.sort();
        } catch (Exception e) {
            System.out.println("some problem with inserting the score");
            // do nothing
        }
        try {
            if (tmp.size() <= 4) {
                // open window to insert name
                DialogManager dialog = gui.getDialogManager();
                String name = dialog.showQuestionDialog("Name",
                        "What is your name?", "");
                ScoreInfo result = new ScoreInfo(name, this.score.getValue());
                this.table.load(tsableResult);
                table.add(result);
                table.save(tsableResult);
            } else if (this.score.getValue() > tmp.getHighScores()
                    .get((tmp.size() - 1)).getScore()) {
                DialogManager dialog = gui.getDialogManager();
                String name = dialog.showQuestionDialog("Name",
                        "What is your name?", "");
                ScoreInfo result = new ScoreInfo(name, this.score.getValue());
                this.table.load(tsableResult);
                table.add(result);
                table.save(tsableResult);
            } else {
                this.table.load(tsableResult);
                table.save(tsableResult);
            }
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new HighScoresAnimation(
                            this.table, KeyboardSensor.SPACE_KEY)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // make score counter to be zero after inserting the score to the table
        this.score.decrease(this.score.getValue());
    }
}