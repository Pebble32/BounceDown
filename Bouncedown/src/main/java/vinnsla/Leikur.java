package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Leikur {
    private final IntegerProperty score = new SimpleIntegerProperty();

    private int Interval = 22;

    private int PALLA_ITERVAL = 1500;


    private final IntegerProperty Level = new SimpleIntegerProperty();

    public void leikLokid(){
        score.set(0);
    }
    public int getPallaInterval() {
        return PALLA_ITERVAL;
    }
    public int getLevel() {
        return Level.get();
    }

    public IntegerProperty levelProperty() {
        return Level;
    }

    public void setLevel(int level) {
        this.Level.set(level);
    }

    public int getInterval() {
        return Interval;
    }

    public void setInterval(int interval) {
        Interval = interval;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public IntegerProperty scoreProperty(){
        return score;
    }

    public void addScore(){
        score.set(score.get() + 1);
    }

    public void nyrLeikur() {

    }

    public void setPallaInterval(int i ) { PALLA_ITERVAL = i;
    }
}
