package vidmot;

import javafx.scene.shape.Circle;

public class Bolti extends Circle {
    private static final double OFFSET = 1;


    public Bolti(){
        FXML_Lestur.lesa(this, "bolti-view.fxml");
    }

    /**
     * Færa boltan um x- og y-ás
     */
    public void afarm(){
        Leikbord p = (Leikbord) this.getParent();
        setCenterX((int)(getCenterX() + p.getWidth() + (int) Math.cos(Math.toRadians(getRotate())) * OFFSET) % (int) p.getWidth());
        setCenterY((int)(getCenterY() + p.getHeight() - (int) Math.sin(Math.toRadians(getRotate())) * OFFSET) % (int) p.getHeight());
    }




}
