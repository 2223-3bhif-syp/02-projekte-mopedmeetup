package at.htl.meetup.view.controller;

import at.htl.meetup.view.App;
import at.htl.meetup.view.DBUtils;
import at.htl.meetup.view.MetaData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import  javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegistrationController implements Initializable{
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!rf_firstname.getText().trim().isEmpty() && !rf_lastname.getText().trim().isEmpty() && !rf_age.getText().trim().isEmpty() &&
                !rf_email.getText().trim().isEmpty() && !rf_password.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event, rf_firstname.getText(), rf_lastname.getText(), rf_email.getText(), Integer.parseInt(rf_age.getText()), rf_password.getText());
                }
                else{
                    System.out.println("Please fill in all information!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all Information to sign up!");
                    alert.show();
                }
            }
        });

        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/login-view.fxml", "Login", null);
            }
        });
    }
}
