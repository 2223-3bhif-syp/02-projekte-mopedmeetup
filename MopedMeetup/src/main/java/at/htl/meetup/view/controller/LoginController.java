package at.htl.meetup.view.controller;

import at.htl.meetup.view.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField rf_email;
    public Button btn_login;
    public PasswordField rf_password;
    public Button btn_signup;
    @FXML
    private Pane content_area;
    @FXML
    private AnchorPane parent;

    @FXML
    private void close_app(MouseEvent event){
        System.exit(0);
    }

  /* @FXML
    private void open_registration(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load((getClass().getResource("/registration-view.fxml")));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.loginUser(event, rf_email.getText(), rf_password.getText());
            }
        });

        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/registration-view.fxml", "Sign Up!", null);
            }
        });
    }
}
