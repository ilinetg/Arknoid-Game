import java.awt.Color;
import java.util.TreeMap;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * MenuAnimation<T> class that implements Menu<T>.
 * @author gal
 * @param <T>
 *            can be of any kind
 */
public class MenuAnimation<T> implements Menu<T> {
    // members
    private TreeMap<String, T> values;
    private TreeMap<String, String> missions;
    private TreeMap<String, Menu<T>> subMenus;
    private TreeMap<String, String> subMenusOptions;
    private T selection;
    private KeyboardSensor board;
    private String subMenuString;

    /**
     * constructor.
     * @param sensor
     *            keyboardSensor.
     */
    public MenuAnimation(KeyboardSensor sensor) {
        this.values = new TreeMap<String, T>();
        this.missions = new TreeMap<String, String>();
        this.selection = null;
        this.board = sensor;
        this.subMenus = new TreeMap<String, Menu<T>>();
        this.subMenusOptions = new TreeMap<String, String>();
        this.subMenuString = null;
    }

    /**
     * incharge of game logic draw.
     * @param d
     *            the surface we draw on.
     * @param dt
     *            frames timing parameter.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // if we pressed to go to sub menu
        if (this.subMenuString != null) {
            this.subMenus.get(this.subMenuString).doOneFrame(d, dt);
            this.selection = this.subMenus.get(this.subMenuString).getStatus();
        } else {

            int i = 1;
            int size = this.missions.keySet().size();
            d.setColor(Color.GREEN);
            // draw the subMenu options
            for (String key : this.subMenusOptions.keySet()) {
                d.drawText(d.getWidth() - 700, d.getHeight() - 300 - (i * 50),
                        "(" + key + ")" + this.subMenusOptions.get(key), 30);
                i++;
            }
            // draw the menu options
            for (String key : this.missions.keySet()) {
                d.drawText(d.getWidth() - 700, d.getHeight() - 300 - (i * 50),
                        "(" + key + ")" + this.missions.get(key), 30);
                i++;
            }
            // if one of the keys that statr one of the missions is pressed
            for (String key : this.missions.keySet()) {
                if (this.board.isPressed(key)) {
                    this.selection = this.values.get(key);
                    break;
                }
            }
            // if one of the keys that start one of the missions in the subMenu
            // is pressed
            for (String key : this.subMenusOptions.keySet()) {
                if (this.board.isPressed(key)) {
                    this.selection = this.values.get(key);
                    break;
                }
            }
            for (String key : subMenus.keySet()) {
                if (this.board.isPressed(key)) {
                    this.subMenuString = key;
                }
            }
        }
    }

    /**
     * @return if the game should stop
     */
    @Override
    public boolean shouldStop() {
        boolean g = (this.selection != null);
        return g;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.values.put(key, returnVal);
        this.missions.put(key, message);
    }

    @Override
    public T getStatus() {
        T giveValue = this.selection;
        this.selection = null;
        this.subMenuString = null;
        return giveValue;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.subMenusOptions.put(key, message);
        this.subMenus.put(key, subMenu);
    }

}
