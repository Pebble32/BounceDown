package vidmot;

import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Pallur extends Rectangle {
    public Pallur(){
        FXML_Lestur.lesa(this, "pallur-view.fxml");


    }

    public void afram(){
        setY(getY() - 1.5);
    }
}
