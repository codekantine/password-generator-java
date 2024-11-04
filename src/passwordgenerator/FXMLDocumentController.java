package passwordgenerator;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label passwordLabel;
    @FXML
    private Label notificationLabel;
    @FXML
    private Button passwordButton;
    @FXML
    private Hyperlink licenseHyperlink;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        passwordButton.setOnAction(this::handlePasswordButtonAction);
        licenseHyperlink.setOnAction(this::handleLicenseHyperlinkAction);
    }

    protected void handlePasswordButtonAction(final ActionEvent event) {
        new Password();
        passwordLabel.setText(Password.getPassword());
        try {
            StringSelection selection = new StringSelection(Password.getPassword());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            notificationLabel.setText("copied to clipboard");
        } catch (Exception e) {
            System.out.println("WARN: Could not copy string to clipboard!\n" + e);
            notificationLabel.setText("");
        }
    }

    protected void handleLicenseHyperlinkAction(final ActionEvent event) {
        final String location = getClass().getResource("LICENSE.txt").toExternalForm();
        final WebView webView = new WebView();
        final Stage stage = new Stage();
        webView.getEngine().load(location);
        stage.setScene(new Scene(webView, 600, 300));
        stage.setTitle("Apache License 2.0");
        stage.setResizable(false);
        stage.show();
    }
}
