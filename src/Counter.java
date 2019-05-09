/**
 * Counter class.
 * @author gal
 */
public class Counter {
    // members
    private int count;

    /**
     * constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * constructor.
     * @param x
     *            - number of counter.
     */
    public Counter(int x) {
        this.count = x;
    }

    /**
     * add number to current count.
     * @param number
     *            to add to the counter
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     * @param number
     *            to decreease from the counter
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * @return get current count.
     */
    public int getValue() {
        return this.count;
    }

    /**
     * @param x
     *            the value we give the counter.
     */
    public void setValue(int x) {
        this.count = x;
    }
}
