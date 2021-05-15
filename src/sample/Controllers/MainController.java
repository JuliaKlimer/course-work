package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
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
        Button button = new Button();
        button.setText("Open a New Window");
        csharpButton.setOnAction(actionEvent -> {
            try {
                csharpButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/UI/sampleCsharp.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

               // stage.initOwner(primaryStage);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
