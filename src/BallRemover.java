/**
 * class BallRemover that implements HitListener.
 * @author gal
 */
public class BallRemover implements HitListener {
    // members
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel
     *            the level in the game.
     * @param removedBalls
     *            counter of how many balls to remove.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.remainingBalls = removedBalls;
        this.gameLevel = gameLevel;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitNum() <= 0) {
            hitter.removeFromGame(this.gameLevel);
            this.remainingBalls.decrease(1);
        }
    }
}
