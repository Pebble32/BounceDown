package vidmot;
import vinnsla.Data;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import vinnsla.Leikur;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class BounceController {
    @FXML
    private Label fxScore;
    private static final HashMap<KeyCode, Stefna> map = new HashMap<>();
    @FXML
    private Leikbord fxLeikbord;
    @FXML
    private BorderPane fxBorderPane;
    private Timeline t;
    private final Leikur leikur = new Leikur();
    private static final String skilabod = "Þú dóst :( ";
    private static final String HALDA_AFRAM = "Viltu prófa aftur eða skrá sig á score lista?";
    private final Data data = Data.getInstance();


    public void initialize() {
        fxLeikbord.getStyleClass().add("bord");
    }

    /**
     * KeyFram fall sem ýtir pöllum upp og uppfærir stig
     * if-setning athugar hvort það er búið að snerta toppinn eða botninn
     */
    public void hefjaLeik() {
        leikur.nyrLeikur();
        KeyFrame k = new KeyFrame(Duration.millis(leikur.getInterval()), e -> {
            setScore();
            fxLeikbord.aframPallar();
            setStefna(Stefna.NIDUR, 2);
            if (fxLeikbord.erBoltiATopnum() || fxLeikbord.erBoltiABotni()){
                leikLokid();
            }
        });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    /**
     * Fall sem stoppar animation og kallar á synaSkilabod() til að birta Alert glugga
     */
    public void leikLokid(){
        t.stop();
        Platform.runLater(() -> {
            try {
                synaSkilabod();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Hendir í Alert glugga
     * if-setning biður eftir það er ýtt á í lagi og heldur áfram
     * else sendir gögn í data og sendir notenda í loka glugga
     * @throws IOException e
     */
    private void synaSkilabod() throws IOException {
        Alert a = new LostDialog("", BounceApplication.BOUNCEDOWN, skilabod + HALDA_AFRAM);
        Optional<ButtonType> u = a.showAndWait();

        if (u.isPresent() && !u.get().getButtonData().isCancelButton()){
            nyrLeikur();
        } else{
            data.setStig(leikur.getScore());
            data.setLvl(leikur.getLevel());
            lostScene();
        }
    }

    /**
     * FXML lestur fall býrtir loka gluggan
     * @throws IOException e
     */
    private void lostScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("lost-view.fxml")));
        Stage stage = (Stage) fxBorderPane.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Fall til að byrja nýjan leik
     * Kallar á föll til að endurstilla palla/bolta
     * Núllstilla stig og level
     * Byrja Timeline frá upphafi
     */
    public void nyrLeikur(){
        fxLeikbord.nyrLeikur();
        leikur.nyrLeikur();
        t.setRate(1);
        t.playFromStart();
    }

    /**
     * Setur stefnu og hvers oft það á að endurtaka köllun á þessu falli
     * Gert til þess að bolti færist meira smooth á leikborði
     * @param s Stefna
     * @param x ítrenir
     */
    public void setStefna(Stefna s, int x){
        for (int i = 0; i < x; i++){
            fxLeikbord.getBolti().setRotate(s.getGradur());
            fxLeikbord.getBolti().afarm();
        }
    }

    /**
     * Mapping fall til að setja inn stefnu fyrir ákveða takka
     * try catch gripur allt sem er ekki örvartakki hægri/vinstri
     * @param s scene frá BounceApplication fyrir eventFilter
     */
    public void orvatakkar(Scene s) {
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

    /**
     * Uppfæra score og level í Leikur klasanum og setja það í label
     * Aukað hraði og level eftir hverja 1009ms
     */
    private void setScore() {
        int i = leikur.getScore();
        if (i % 1009 == 0) { //1009 er prim tala
            leikur.setLevel(leikur.getLevel()+1);
            double current = t.getRate();
            t.setRate(current + current * 0.2);
        }
        leikur.addScore();
        fxScore.setText("Stig:" + leikur.getScore() + "\tLevel: " + leikur.getLevel());
    }
}