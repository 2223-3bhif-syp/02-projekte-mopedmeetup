package at.htl.meetup.view;

import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import java.net.URL;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController {
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
