package at.htl.meetup.view;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import  javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegistrationController {
    public PasswordField tf_password;
    public PasswordField tf_conf_password;
    public TextField tf_firstname;
    public TextField tf_email;
    public Button button_login;

    public void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void back_to_menu(MouseEvent event) {
        App.stage.getScene().setRoot(MetaData.parent);
    }
}
