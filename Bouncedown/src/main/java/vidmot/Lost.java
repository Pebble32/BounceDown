package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vinnsla.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Lost {
    @FXML
    public ListView<String> fxListView = new ListView<>();
    @FXML
    public Label fxLokaStig;
    @FXML
    public Button fxLoka;
    @FXML
    public Button fxSpilaAftur;
    @FXML
    public AnchorPane fxAnchorPane;
    @FXML
    private Button fxAddScore;
    @FXML
    private TextField fxUsername;
    private final Data data = Data.getInstance();
    private final File saveFile = new File("Scoreboard.txt");


    /**
     * Kalla á binding fall
     * Ná í gögn ur Data og setja það í Label (fxLokaStig)
     * Skoða hvort txt skrá er til, ef það er til bæta inn öllum línum inn í ListView
     */
    public void initialize(){
        iLagi();
        fxLokaStig.setText(data.getStig() + " stig og " + data.getLvl() + "lvl");

        if (saveFile.exists()){
            try {
                List<String> fileLoaded = Files.readAllLines(saveFile.toPath());
                fxListView.getItems().addAll(fileLoaded);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Binda saman fxUsername og Node (fxAddScore) svo það er ekki hægt að ýta á takka ef TextField er tómt
     */
    private void iLagi() {
        Node iLagi = fxAddScore;
        iLagi.disableProperty().bind(fxUsername.textProperty().isEmpty());
    }

    /**
     * Takki til að bæta sig inn á scoreboard listan ef það er sett inn usernamið
     */
    public void addScoreHandler(){
            fxListView.getItems().add(fxUsername.getText() + " || " + data.getStig() + " stig");
            fxUsername.setText(null);
            fxUsername.setDisable(true);
            try {
                saveFile.createNewFile(); // ef txt skrá er eytt út
                Files.write(saveFile.toPath(), fxListView.getItems().subList(0,fxListView.getItems().size()));
            }
            catch (IOException e){
                e.printStackTrace();
            }
    }

    /**
     * Takki til að loka forrit
     */
    public void lokaHandler() {
        Stage stage = (Stage) fxLoka.getScene().getWindow();
        stage.close();
    }

    /**
     * Takki sem ef er ýtt á breytir um scene og byrja leik upp á nýtt eins og forrit er keyrt í fyrsta skiptið
     * @throws IOException e
     */
    public void spialAfturHandler() throws IOException {
        Stage stage = (Stage) fxAnchorPane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BounceApplication.class.getResource("bounce-view.fxml"));
        Parent root = fxmlLoader.load();
        BounceController sc = fxmlLoader.getController();

        stage.setTitle("BOUNCEDOWN!");
        Scene scene = new Scene(root, 400, 500);
        sc.orvatakkar(scene);
        stage.setScene(scene);
        stage.show();
        sc.hefjaLeik();
    }
}
