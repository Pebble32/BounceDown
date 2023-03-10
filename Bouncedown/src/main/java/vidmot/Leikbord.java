package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import vinnsla.Leikmadur;
import vinnsla.Leikur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Random;

public class Leikbord extends Pane {
    @FXML
    private Bolti fxBolti;
    private static final Random random = new Random();
    private Timeline t;
    private final ObservableList<Pallur> pallaListi = FXCollections.observableArrayList();
    public ObservableList<Pallur> getPallaListi() {
        return pallaListi;
    }

    public Leikbord(){
        FXML_Lestur.lesa(this, "leikbord-view.fxml");
        setjaPallaILista();
        fxBolti.centerYProperty().addListener((observable, oldvalue,newValue) -> {
            for (int i = 0; i < pallaListi.size(); i++){
                if (erArekstur(pallaListi.get(i))){
                    fxBolti.setRotate(Stefna.UPP.getGradur());
                    fxBolti.afarm();
                }
            } /*if (erBoltiABotni() || erBoltiATopnum()){
                t.stop();
            }*/
          });

        pallaKeyFrame();
    }



    public void pallaKeyFrame(){
        KeyFrame k = new KeyFrame(Duration.millis(1600), e -> {
            nyPallur();
        });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    public Bolti getBolti() {
        return fxBolti;
    }
    public boolean erArekstur(Pallur d){
        return fxBolti.getBoundsInParent().intersects(d.getBoundsInParent());
    }

    public boolean erBoltiABotni(){
        return fxBolti.getCenterY() == 300;
    }

    public boolean erBoltiATopnum(){
        return fxBolti.getCenterY() == 0;
    }

    public void aframPallar(){
        for (Pallur pallur : pallaListi) {
            pallur.afram();
        }
    }
    public void setjaPallaILista(){
        for (int i = 1; i < 6; i++){
            pallaListi.add((Pallur) this.getChildren().get(i));
        }
    }
    public void nyPallur(){
        int x = random.nextInt((int)(this.getWidth()-pallaListi.get(0).getWidth()*2))+(int)pallaListi.get(0).getWidth();
        Pallur p = new Pallur();
        p.setX(x);
        p.setY(this.getHeight());
        pallaListi.add(p);
        this.getChildren().add(p);
    }
}
