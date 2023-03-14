package vidmot;

import javafx.scene.shape.Rectangle;
public class Pallur extends Rectangle {
    public Pallur(){
        FXML_Lestur.lesa(this, "pallur-view.fxml");
    }

    /**
     * Færa palla um 1.5 upp í hverja skipti fall er kallað
     */
    public void afram(){
        setY(getY() - 1.5);
    }
}
