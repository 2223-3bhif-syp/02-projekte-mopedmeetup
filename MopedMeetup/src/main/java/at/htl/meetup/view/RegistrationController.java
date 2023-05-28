package at.htl.meetup.view;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import  javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegistrationController {
    public void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void back_to_menu(MouseEvent event) {
        App.stage.getScene().setRoot(MetaData.parent);
    }
}
