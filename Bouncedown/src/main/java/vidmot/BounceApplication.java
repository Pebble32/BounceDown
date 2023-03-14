package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BounceApplication extends Application {
    public static final String BOUNCEDOWN = "BOUNCEDOWN!";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BounceApplication.class.getResource("bounce-view.fxml"));
        Parent root = fxmlLoader.load();
        BounceController sc = fxmlLoader.getController();

        stage.setTitle(BOUNCEDOWN);
        Scene scene = new Scene(root, 400, 500);
        sc.orvatakkar(scene); // kalla á mapping fall fyrir takka
        stage.setScene(scene);
        stage.show();
        sc.hefjaLeik(); // kalla á keyframe fall til að byrja leik
    }



    public static void main(String[] args) {
        launch();
    }
}