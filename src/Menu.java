/**
 * Menu Interface that extends Animation.
 * @author gal
 * @param <T>
 *            can be of any kind
 */
public interface Menu<T> extends Animation {
    /**
     * Add a task to the menu.
     * @param key
     *            key to press for entering the task.
     * @param message
     *            the massage to appear on the menu.
     * @param returnVal
     *            the task .
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return the task we run.
     */
    T getStatus();

    /**
     * @param key
     *            key to press for entering the task.
     * @param message
     *            the massage to appear on the subMenu.
     * @param subMenu
     *            the subMenu we add to the existing menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}