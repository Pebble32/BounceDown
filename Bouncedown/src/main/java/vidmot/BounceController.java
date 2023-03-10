package vidmot;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.css.StyleOrigin;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import vinnsla.Leikur;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Optional;

public class BounceController {
    @FXML
    private Label fxScore;
    private static final HashMap<KeyCode, Stefna> map = new HashMap<KeyCode, Stefna>();
    @FXML
    private Leikbord fxLeikbord;
    @FXML
    private Bolti fxBolti;
    private Timeline t;
    private ObservableList<Pallur> p;
    private final Leikur leikur = new Leikur();
    private final String skilabod = "Þú dóst :(";
    private final String HALDA_AFRAM = "Halda áfram?";


    public void initialize(){
        p = fxLeikbord.getPallaListi();
        fxLeikbord.getStyleClass().add("bord");
    }


    public void hefjaLeik() {
        KeyFrame k = new KeyFrame(Duration.millis(leikur.getInterval()), e -> {
            fxLeikbord.aframPallar();
            setStefna(Stefna.NIDUR, 2);
            setScore();
            /*if (fxLeikbord.erBoltiATopnum() || fxLeikbord.erBoltiABotni()){
                System.out.println("lokið");
                leikLokid();
            }*/
        });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    public void leikLokid(){
        t.stop();
        Platform.runLater(() -> synaSkilabod(skilabod));
    }

    private void synaSkilabod(String s){
        Alert a = new LostDialog("", BounceApplication.BOUNCEDOWN, s + HALDA_AFRAM);
        Optional<ButtonType> u = a.showAndWait();
        if (u.isPresent() && !u.get().getButtonData().isCancelButton())
            nyrLeikur();
    }

    public Timeline getT() {
        return t;
    }

    public void setT(Timeline t) {
        this.t = t;
    }
    public void nyrLeikur(){
        leikur.nyrLeikur();
        t.play();
    }

    public void setStefna(Stefna s, int x){
        for (int i = 0; i < x; i++){
            fxLeikbord.getBolti().setRotate(s.getGradur());
            fxLeikbord.getBolti().afarm();
        }
    }
    public void orvatakkar(BounceController sc, Scene s) {
        map.put(KeyCode.RIGHT, Stefna.HAEGRI);
        map.put(KeyCode.LEFT, Stefna.VINSTRI);
        s.addEventFilter(KeyEvent.ANY,      //KeyEvents eru sendar á Scene
                event -> {      // lambda fall - event er parameter
                    try {
                        if (event.getCode() == KeyCode.LEFT){
                            setStefna(Stefna.VINSTRI, 5);
                        } else if (event.getCode() == KeyCode.RIGHT) {
                            setStefna(Stefna.HAEGRI,5);
                        }
                    } catch (NullPointerException e){
                        event.consume();
                    }
                });
    }

    private void setScore() {
        int i = leikur.getScore();
        if ((leikur.getInterval()-1) > 10){
            if (i % 1009 == 0) { //1009 er prim tala
                leikur.setInterval(leikur.getInterval() - 1);
                hefjaLeik();
                leikur.setLevel(leikur.getLevel()+1);
            }
        }
        leikur.addScore();
        fxScore.setText("Stig:" + leikur.getScore() + "\tLevel: " + leikur.getLevel());

    }
}