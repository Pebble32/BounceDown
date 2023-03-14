package vidmot;

import javafx.scene.control.*;

public class LostDialog extends Alert{
    private static final String I_LAGI = "Í lagi";
    public static final ButtonType BTYPE = new ButtonType(I_LAGI,
            ButtonBar.ButtonData.OK_DONE);
    private static final String HAETTA_VID = "Hætta við";
    public static final ButtonType HTYPE = new ButtonType(HAETTA_VID,
            ButtonBar.ButtonData.CANCEL_CLOSE); // ButtonType er merktur með CANCEL_CLOSE (er enum);


    /**
     * Smiður fyrir LostDialog sem eftir frá Alert (ekki smiður sem erfir heldur klasinn sem erfir)
     * @param t title af glugga
     * @param h header af dialog
     * @param q Skilaboð
     */
    public LostDialog(String t, String h, String q){
        super(AlertType.NONE, q, BTYPE, HTYPE);  // kallar á smið yfirklasans
        setTitle(t); // setja Title
        setHeaderText(h); // Setja upp header
    }
}
