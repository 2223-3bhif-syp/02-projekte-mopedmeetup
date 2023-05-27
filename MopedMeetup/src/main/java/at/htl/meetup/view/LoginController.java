package at.htl.meetup.view;

import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

import java.sql.SQLException;
import java.util.EventListener;
public class LoginController {
    @FXML
    private void close_app(MouseEvent event){
        System.exit(0);
    }

    public void open_registration(MouseEvent event) {
    }
}
