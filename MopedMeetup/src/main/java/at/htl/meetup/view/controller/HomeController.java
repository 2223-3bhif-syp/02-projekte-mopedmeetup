package at.htl.meetup.view.controller;

import at.htl.meetup.view.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button btn_logout;

    @FXML
    private Label label_welcome;
    @FXML
    private Button btn_create_mm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/login-view.fxml", "Login!", null);
            }
        });
        btn_create_mm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/create-mm-view.fxml", "Create Meetup!", null);
            }
        });
    }

    public void setUserInformation(String firstName){
        label_welcome.setText("Welcome " + firstName + "!");
    }
}
