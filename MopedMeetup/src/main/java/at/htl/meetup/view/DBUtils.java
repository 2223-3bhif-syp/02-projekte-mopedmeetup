package at.htl.meetup.view;

import at.htl.meetup.controller.UserRepository;
import at.htl.meetup.entity.User;
import at.htl.meetup.view.controller.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Objects;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;

        try{
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("/"+fxmlFile));
            root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static Long signUpUser(ActionEvent event, String firstName, String lastName, String email, int age, String password){
        UserRepository user = new UserRepository();
        boolean isUserExisting = user.isUserExisting(email, password);

        if(isUserExisting){
            System.out.println("User already exists!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You cannot use this username!");
            alert.show();
            return null;
        }
        else{
            User insUser = new User(firstName, lastName, password, email, age);
            user.insert(insUser);
            return user.getByEmail(email).getId();
        }
    }

    public static Long loginUser(ActionEvent event, String email, String password){
        System.out.println("Login user");
        UserRepository user = new UserRepository();
        ObservableList<User> users = FXCollections.observableArrayList(user.getAll());


        if(users.isEmpty() || !user.isUserExisting(email, password)){
            System.out.println("User not found!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provided credentials are incorrect!");
            alert.show();
            return null;
        }
        else{
            return user.getByEmail(email).getId();
        }
    }
}
