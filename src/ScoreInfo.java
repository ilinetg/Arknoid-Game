import java.io.Serializable;

/**
 * ScoreInfo class.
 * @author gal
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {
    // members
    private String player;
    private int result;

    /**
     * constructor.
     * @param name
     *            player's name.
     * @param score
     *            player's score.
     */
    public ScoreInfo(String name, int score) {
        this.player = name;
        this.result = score;
    }

    /**
     * @return player's name.
     */
    public String getName() {
        return this.player;
    }

    /**
     * @return player's score.
     */
    public int getScore() {
        return this.result;
    }

    @Override
    public int compareTo(ScoreInfo other) {
        return (other.getScore() - this.getScore());

    }
}
