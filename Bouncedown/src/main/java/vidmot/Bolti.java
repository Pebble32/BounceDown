package vidmot;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Bolti extends Circle {
    private Leikbord fxLeikbord;
    private final double OFFSET = 1;
    @FXML
    private Bolti fxBolti;


    public Bolti(){
        FXML_Lestur.lesa(this, "bolti-view.fxml");
    }

    public void afarm(){
        // bara prófa eitthvað
        Leikbord p = (Leikbord) this.getParent();
        setCenterX((int)(getCenterX() + p.getWidth() + (int) Math.cos(Math.toRadians(getRotate())) * OFFSET) % (int)p.getWidth());
        setCenterY((int)(getCenterY() + p.getHeight() - (int) Math.sin(Math.toRadians(getRotate())) * OFFSET) % (int)p.getHeight());
    }




}
