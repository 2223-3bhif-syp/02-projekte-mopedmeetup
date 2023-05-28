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
    public Button button_login;
    public Button btn_login;
    public PasswordField rf_age;
    public PasswordField rf_password;
    public Button btn_register;
    public TextField rf_firstname;
    public TextField rf_email;
    public TextField rf_lastname;

    public void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void back_to_menu(MouseEvent event) {
        App.stage.getScene().setRoot(MetaData.parent);
    }
}
