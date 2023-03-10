package vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 *
 * EÞH - changed to include caching of controllers
 */
public class ViewSwitcher {

    private static final Map<View, Parent> cache = new HashMap<>();

    private static final Map<View, Object> controllers = new HashMap<>();
    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;
            FXMLLoader loader = null;
            if (cache.containsKey(view)) {
                System.out.println("Loading from cache");
                root = cache.get(view);
            } else {
                System.out.println("Loading from FXML");
                loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
                root = loader.load();

                cache.put(view, root);
                scene.setRoot(root);
                controllers.put(view, loader.getController());
            }
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object lookup(View v) {
        return controllers.get(v);
    }
}