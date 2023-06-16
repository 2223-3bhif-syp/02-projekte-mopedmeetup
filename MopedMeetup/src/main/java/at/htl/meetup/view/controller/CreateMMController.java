package at.htl.meetup.view.controller;

import at.htl.meetup.controller.LocationRepository;
import at.htl.meetup.controller.MeetupRepository;
import at.htl.meetup.controller.UserRepository;
import at.htl.meetup.entity.Location;
import at.htl.meetup.entity.Meetup;
import at.htl.meetup.view.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CreateMMController implements Initializable{
    public TextField cr_mm_street;
    public TextField cr_mm_name;
    public TextField cr_mm_city;
    public TextField cr_mm_zip;
    public TextField cr_mm_date;
    public TextField cr_mm_description;
    public Label lb_errormsg;
    public Button btn_create;

    public Location location;
    public LocationRepository locationRepository = new LocationRepository();

    public Meetup meetup;
    public MeetupRepository meetupRepository = new MeetupRepository();
    public Long userId = UserSession.getId();

    public UserRepository userRepository = new UserRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(cr_mm_street.getText().isEmpty() || cr_mm_name.getText().isEmpty() || cr_mm_city.getText().isEmpty() || cr_mm_zip.getText().isEmpty() || cr_mm_date.getText().isEmpty() || cr_mm_description.getText().isEmpty()){
                    lb_errormsg.setText("Please fill out all fields!");
                    return;
                }else if(cr_mm_zip.getText().length() != 4 || !cr_mm_zip.getText().matches("[0-9]+")) {
                    lb_errormsg.setText("Please enter a valid zip code!");
                    return;
                }else {
                    location = new Location(cr_mm_street.getText(), cr_mm_name.getText(), cr_mm_city.getText(), Integer.parseInt(cr_mm_zip.getText()));
                    locationRepository.insert(location);
                    userRepository.getAll();
                    meetup = new Meetup(userRepository.getById(userId), location, cr_mm_description.getText(), LocalDateTime.parse(cr_mm_date.getText()));
                    meetupRepository.insert(meetup);
                    lb_errormsg.setText("Meetup created!");
                }
            }
        });
    }


}
