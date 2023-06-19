package at.htl.meetup.view;

import at.htl.meetup.controller.Database;
import at.htl.meetup.database.SqlRunner;
import at.htl.meetup.database.SqlScript;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class App extends Application {
    public static Stage stage = null;
    @Override
    public void start(Stage stage) throws IOException {

        try(Connection connection = Database.getDataSource().getConnection()) {
            if(SqlRunner.tableExist(connection, "MM_USER") &&
                SqlRunner.tableExist(connection, "MM_MEETUP") &&
                SqlRunner.tableExist(connection, "MM_PARTICIPANTS") &&
                SqlRunner.tableExist(connection, "MM_LOCATION") ){
                System.out.println("Tables exist");
            }
            else {
                System.out.println("Tables don't exist, creating...");
                SqlRunner.dropAndCreateTablesWithExampleData();
            }

        }
        catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        Parent root = FXMLLoader.load(getClass().getResource("/login-view.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Login");
        stage.setScene(scene);
        this.stage = stage;
        MetaData.parent = root;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
