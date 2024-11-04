package passwordgenerator;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author codekantine
 * @version 1.0.0
 */
public class PasswordGenerator extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        final URL fxmlUrl = getClass().getResource("FXMLDocument.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        fxmlLoader.setController(new FXMLDocumentController());
        final Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root, 350, 125));
        stage.setTitle("Password Generator");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
