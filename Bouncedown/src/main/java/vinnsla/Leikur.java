package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Leikur {
    private final IntegerProperty score = new SimpleIntegerProperty();
    private int Interval = 22;
    private final IntegerProperty Level = new SimpleIntegerProperty();

    /**
     * Ná í level
     * @return level gildi sem int
     */
    public int getLevel() {
        return Level.get();
    }

    /**
     * Uppfæra level
     * @param level frá BounceController sem er uppfært
     */
    public void setLevel(int level) {
        this.Level.set(level);
    }

    /**
     * Ná í ítrenir
     * @return Interval fyrir BounceController
     */

    public int getInterval() {
        return Interval;
    }

    /**
     * Setja nýtt interval
     * @param interval frá BounceController
     */
    public void setInterval(int interval) {
        Interval = interval;
    }

    /**
     * Ná í stig gildi
     * @return score sem int
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Bæta inn 1 stig
     */
    public void addScore(){
        score.set(score.get() + 1);
    }

    /**
     * Núll stilla gildi þegar nýtt leikur er byrjað
     */
    public void nyrLeikur() {
        setLevel(0);
        score.set(0);
    }
}
