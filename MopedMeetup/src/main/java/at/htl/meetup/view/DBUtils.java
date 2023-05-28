package at.htl.meetup.view;

import at.htl.meetup.controller.UserRepository;
import at.htl.meetup.entity.User;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String firstName){
        Parent root = null;

        if(firstName != null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                HomeController homeController = loader.getController();
                homeController.setUserInformation(firstName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try{
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String firstName, String email, String password, String confPassword){
        UserRepository user = new UserRepository();
        ObservableList<User> users = FXCollections.observableArrayList(user.getAll());

        if(!users.isEmpty()){
            System.out.println("User already exists!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You cannot use this username!");
            alert.show();
        }
        else{
           // add user
        }
    }
}
