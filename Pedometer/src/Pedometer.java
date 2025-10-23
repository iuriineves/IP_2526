public class Pedometer {

    private boolean isOn;
    private int steps;

    public Pedometer() {
        isOn = true;
    }

    /**
     * Toggles pedometer state between on and off. Defaults to {@code true}.
     * @see #isOn()
     */
    public void toggle() {
        isOn = !isOn;
    }

    /**
     * Increments the steps by one, if the pedometer is on.
     * @see #getSteps()
     */
    public void step() {
        if (isOn) steps++;
    }

    /**
     * Retrieves the steps registered.
     * @return steps
     * @see #step()
     */
    int getSteps() { return steps; };

    /**
     * Sets steps back to 0.
     */
    void reset() { steps = 0; }

   /**
     * Retrieves the pedometer state.
     * @return pedometer state
     * @see #toggle()
     */
    boolean isOn() { return isOn; }
}
