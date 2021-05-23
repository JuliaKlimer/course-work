package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button csharpButton;

    @FXML
    private Button javaButton;

    @FXML
    private Button oopButton;

    @FXML
    private Button newButton;

    @FXML
    private Label labelQuestion;

    @FXML
    void initialize() {
        csharpButton.setOnAction(actionEvent -> {
            try {
                csharpButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/UI/sampleCsharp.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Simple Testing");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        javaButton.setOnAction(actionEvent -> {
            try {
                javaButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/UI/sampleJava.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Simple Testing");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        oopButton.setOnAction(actionEvent -> {
            try {
                oopButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/UI/sampleOop.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Simple Testing");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        newButton.setOnAction(actionEvent -> {
            try {
                newButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/UI/sampleNew.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Simple Testing");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
