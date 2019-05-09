import biuoop.DrawSurface;

/**
 * EndGame class that implements Animation - is the showen screen when game
 * ends.
 * @author gal
 */
public class EndGame implements Animation {
    // members
    private String anouncment;

    /**
     * constructor.
     * @param s
     *            the anouncment to the player.
     */
    public EndGame(String s) {
        this.anouncment = s;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, this.anouncment, 32);
        d.drawText(d.getWidth() / 2 - 100, d.getHeight() - 100,
                "to continue press space", 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

}
