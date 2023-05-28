package at.htl.meetup.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class LoginController {
    public Label button_signup;
    public TextField rf_email;
    public Button btn_login;
    public PasswordField rf_password;
    @FXML
    private Pane content_area;
    @FXML
    private AnchorPane parent;

    @FXML
    private void close_app(MouseEvent event){
        System.exit(0);
    }

    @FXML
    private void open_registration(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load((getClass().getResource("/registration_view.fxml")));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
}
