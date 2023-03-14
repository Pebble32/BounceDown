package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Leikbord extends Pane {
    @FXML
    private Bolti fxBolti;
    private static final Random random = new Random();
    private static final int PALLA_INTERVAL = 1600;
    private Timeline t;
    private final ObservableList<Pallur> pallaListi = FXCollections.observableArrayList();

    public Leikbord(){
        FXML_Lestur.lesa(this, "leikbord-view.fxml");
        setjaPallaALeikbord();
        nyBolti();
        athugaArekstur();
        pallaKeyFrame();
    }

    /**
     * Athuga hvort Bolti og pallur eru að snertast saman
     * Ef það er gert Bolti færist upp með pallanum
     * Ef bolti er að snerta toppinn eða botnin pallar stoppa
     */
    private void athugaArekstur() {
        fxBolti.centerYProperty().addListener((observable, oldvalue,newValue) -> {
            for (Pallur pallur : pallaListi) {
                if (erArekstur(pallur)) {
                    fxBolti.setRotate(Stefna.UPP.getGradur());
                    fxBolti.afarm();
                }
            } if (erBoltiABotni() || erBoltiATopnum()){
                t.stop();
            }
        });
    }

    /**
     * Fall til að byrja nýja leik og spila aftur Timeline
     * Hreinsar palla og bolta. Býr til nýja bolta og allar.
     */
    public void nyrLeikur() {
        hreinsaPalla();
        fxBolti = nyBolti();
        nyrLeikPallar();
        athugaArekstur();
        t.playFromStart();
    }

    /**
     * Býr til nýja palla fyrir nýjan leik og bætir þeim öll í lista og child af leikbord
     */
    private void nyrLeikPallar() {
        Pallur u = new Pallur();
        if (pallaListi.isEmpty()){
            for (int i = 420; i > 0; i -= 150){
                int x = random.nextInt((int)(this.getWidth()-u.getWidth()*2)+(int)u.getWidth());
                Pallur p = new Pallur();
                pallaListi.add(p);
                p.setX(x);
                p.setY(i);
                getChildren().add(p);
            }
        }
    }

    /**
     * Eyðir út bolta ef það er til og býr til nýjan bolta. Bætir það sem child af leikbord
     * x og y gildi er presettar í bolti-view.fxml þannig þurfum ekki að kalla á það hér
     * @return nýja Bolta
     */
    private Bolti nyBolti() {
        if (fxBolti != null){
            getChildren().remove(fxBolti);
        }
        fxBolti = new Bolti();
        getChildren().add(fxBolti);
        return fxBolti;
    }

    /**
     * Eyðir allar pallar sem eru children af leikborð og tæmir svo listan pallaListi
     */
    private void hreinsaPalla() {
        for (Pallur f : pallaListi){
            getChildren().remove(f);
        }
        pallaListi.clear();
    }

    /**
     * KeyFram fall sem býr til nýja palla á 1600ms
     */
    public void pallaKeyFrame(){
        KeyFrame k = new KeyFrame(Duration.millis(PALLA_INTERVAL), e -> nyPallur());
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    /**
     * Getter fyrir bolta
     * @return fxBolti
     */
    public Bolti getBolti() {
        return fxBolti;
    }

    /**
     * Athuga árekstur milli bolta og pall
     * @param d Pallur sem er athugað árekstur
     * @return true ef það er árekstur milli bolta og pallinn
     */
    public boolean erArekstur(Pallur d){
        return fxBolti.getBoundsInParent().intersects(d.getBoundsInParent());
    }

    /**
     * Athugar hvort bolti er á botnum
     * @return true ef bolti er að snerta botnin af leikborði
     */
    public boolean erBoltiABotni(){
        return fxBolti.getCenterY() == (int)(getHeight()- fxBolti.getRadius());
    }

    /**
     * Athugar hvort bolti er á topnum
     * @return true ef bolti er að snerta toppinn af leikborði
     */
    public boolean erBoltiATopnum(){
        return fxBolti.getCenterY() == 0;
    }

    /**
     * Fall sem fer í gegnum allar pallar í listanum og ýtir þeim áfram (úpp)
     */
    public void aframPallar(){
        for (Pallur pallur : pallaListi) {
            pallur.afram();
        }
    }

    /**
     * Setja palla á leikbord þegar það er fyrst kveit á forritinu
     */
    public void setjaPallaALeikbord(){
        if (pallaListi.isEmpty()){
            for (int i = 420; i > 0; i -= 150){
                int x = random.nextInt(400);
                Pallur p = new Pallur();
                pallaListi.add(p);
                p.setX(x);
                p.setY(i);
                getChildren().add(p);
            }
        }
    }


    /**
     * Fall sem býr til nýja pall á random x-ás stöðu sem er innan leikbord width og neðst á leibord
     * Bætir svo það á lista og sem child af leikbord
     */
    public void nyPallur(){
        int x = random.nextInt((int)(this.getWidth()-pallaListi.get(0).getWidth()*2))+(int)pallaListi.get(0).getWidth();
        Pallur p = new Pallur();
        p.setX(x);
        p.setY(this.getHeight());
        pallaListi.add(p);
        this.getChildren().add(p);
    }
}
