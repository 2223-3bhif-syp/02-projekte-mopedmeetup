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

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateMMController implements Initializable{
    public TextField cr_mm_street;
    public TextField cr_mm_name;
    public TextField cr_mm_city;
    public TextField cr_mm_zip;
    public DatePicker cr_mm_date;
    public TextArea cr_mm_description;
    public Label lb_errormsg;
    public Button cr_mm_btn_submit;

    public Location location;
    public ImageView btn_back_to_menu;

    public LocationRepository locationRepository = new LocationRepository();

    public Meetup meetup;
    public MeetupRepository meetupRepository = new MeetupRepository();
    public Long userId = UserSession.getId();
    public UserRepository userRepository = new UserRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cr_mm_btn_submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(cr_mm_street.getText().isEmpty() || cr_mm_name.getText().isEmpty() || cr_mm_city.getText().isEmpty() || cr_mm_zip.getText().isEmpty() || cr_mm_date.getValue().equals(null) || cr_mm_description.getText().isEmpty()){
                    lb_errormsg.setText("Please fill out all fields!");
                }else if(cr_mm_zip.getText().length() != 4 || !cr_mm_zip.getText().matches("[0-9]+")) {
                    lb_errormsg.setText("Please enter a valid zip code!");
                }else {
                    LocalDate localDate = cr_mm_date.getValue();
                    LocalDateTime instant = LocalDateTime.from(localDate.atStartOfDay(ZoneId.systemDefault()));

                    location = new Location(cr_mm_street.getText(), cr_mm_name.getText(), cr_mm_city.getText(), Integer.parseInt(cr_mm_zip.getText()));
                    locationRepository.insert(location);
                    userRepository.getAll();
                    meetup = new Meetup(userRepository.getById(userId), location, cr_mm_description.getText(), instant);
                    meetupRepository.insert(meetup);
                    lb_errormsg.setText("Meetup created!");
                }
            }
        });
    }

    public void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void back_to_menu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage currentStage = (Stage) btn_back_to_menu.getScene().getWindow();
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
        currentStage.close();
    }


}
