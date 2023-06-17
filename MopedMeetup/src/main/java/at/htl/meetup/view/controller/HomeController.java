package at.htl.meetup.view.controller;

import at.htl.meetup.controller.UserRepository;
import at.htl.meetup.entity.User;
import at.htl.meetup.view.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
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
        label_welcome.setText("Welcome " + UserRepository.getById(UserSession.getId()).getFirstName() + "!");
       btn_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login-view.fxml", "Login!");
            }
        });
        btn_create_mm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "create-mm-view.fxml", "Create Meetup!");
            }
        });
    }
}
