package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import vinnsla.Leikmadur;
import vinnsla.Leikur;

import java.io.IOException;

public class LostDialog extends Alert{
    private static final String I_LAGI = "Í lagi";
    public static final ButtonType BTYPE = new ButtonType(I_LAGI,
            ButtonBar.ButtonData.OK_DONE);
    private static final String HAETTA_VID = "Hætta við";
    public static final ButtonType HTYPE = new ButtonType(HAETTA_VID,
            ButtonBar.ButtonData.CANCEL_CLOSE); // ButtonType er merktur með CANCEL_CLOSE (er enum);


    public LostDialog(String t, String h, String q){
        super(AlertType.NONE, q, BTYPE, HTYPE);  // kallar á smið yfirklasans
        setTitle(t);
        setHeaderText(h);
    }

    public DialogPane setjaDialogPane(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lostDialog-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void birtaGogn(Leikur l, Leikmadur m){

    }
}
