import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import biuoop.GUI;

/**
 * Ass6Game class.
 * @author gal
 */
public class Ass6Game {
    /**
     * this class starts the game.
     * @param args
     *            the path to the files to set the levels.
     */
    public static void main(String[] args) {
        String path = "level_sets.txt";
        if (args.length != 0) {
            path = args[0];
        }
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        GUI gui = new GUI("Arknoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                gui.getKeyboardSensor());
        menu.addSelection("h", "High scores",
                new ShowHiScoresTask(runner,
                        new HighScoresAnimation(HighScoresTable
                                .loadFromFile(new File("highScores.txt")), "h"),
                        gui.getKeyboardSensor()));
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(
                gui.getKeyboardSensor());
        Map<String, String> levelDif = LevleSetReader.setReader(path);
        Set<String> keys = levelDif.keySet();
        // creating the sub menu with the levels we read from the files
        for (String code : keys) {
            String[] description = code.split(":");
            String d = description[0];
            String b = levelDif.get(code);
            InputStream reader = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(b);
            levels = new ArrayList<LevelInformation>();
            LevelSpecificationReader levelReader = new LevelSpecificationReader();
            InputStreamReader is = new InputStreamReader(reader);
            levels = levelReader.fromReader(is);

            subMenu.addSelection(d, description[1], new LevelRunTask(levels,
                    runner, new GameFlow(runner, gui, 7)));
        }

        menu.addSubMenu("s", "Start Game", subMenu);
        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        });
        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}