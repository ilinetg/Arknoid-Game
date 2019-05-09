import java.util.List;

/**
 * LevelRunTask class that implements Task<Void>.
 * @author gal
 */
public class LevelRunTask implements Task<Void> {
    // members
    private AnimationRunner runner;
    private GameFlow gameFlow;
    private List<LevelInformation> levels;

    /**
     * constructor.
     * @param levelsToRun
     *            list of levels.
     * @param runner
     *            the animation to run
     * @param game
     *            the gameFlow object.
     */
    public LevelRunTask(List<LevelInformation> levelsToRun,
            AnimationRunner runner, GameFlow game) {
        this.runner = runner;
        this.gameFlow = game;
        this.levels = levelsToRun;
    }

    @Override
    public Void run() {
        List<LevelInformation> levelList = this.levels;
        this.gameFlow.runLevels(levelList);
        return null;
    }
}
