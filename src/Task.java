/**
 * Task interface.
 * @author gal
 * @param <T>
 *            can be of any kind.
 */
public interface Task<T> {
    /**
     * @return the kind of the thing we run
     */
    T run();
}
