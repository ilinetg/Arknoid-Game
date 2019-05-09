/**
 * ScoreTrackingListener class that implements HitListener.
 * @author gal
 */
public class ScoreTrackingListener implements HitListener {
    // members
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter
     *            - counter for the score in the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitNum() > 0) {
            this.currentScore.increase(5);
            return;
        }
        if (beingHit.getHitNum() == 0) {
            this.currentScore.increase(10);

            return;
        }
    }
}